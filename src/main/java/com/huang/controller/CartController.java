package com.huang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.domain.BuyCart;
import com.huang.domain.BuyItem;
import com.huang.domain.ProductInfo;
import com.huang.domain.ProductStyle;
import com.huang.service.ProductService;
import com.huang.util.WebUtil;
import com.huang.vo.CartVO;


@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Resource
	private ProductService productService;
	
	@RequestMapping("/add.do")
	public String toAdd(HttpServletResponse response,HttpSession session,CartVO cartvo,Model model){
		BuyCart buycart = (BuyCart)session.getAttribute("cart");
		if(buycart == null){
			buycart = new BuyCart();
			session.setAttribute("cart", buycart);
		}
		WebUtil.addCookie(response, "cart", session.getId(), session.getMaxInactiveInterval());
		if(cartvo.getProductid()!=null&&cartvo.getProductid()>0){
			ProductInfo product = productService.getByIdWithProductStyle(cartvo.getProductid());
			if(product!=null){
				ProductStyle currentStyle = null;
				for(ProductStyle style : product.getStyles()){
					if(style.getId()==cartvo.getStyleid()){
						currentStyle = style;
						break;
					}
				}
				product.getStyles().clear();
				if(currentStyle!=null) product.addProductStyle(currentStyle);
				buycart.addBuyItem(new BuyItem(product));
			}
		}
		if(cartvo.getDirectUrl()!=null&&!cartvo.getDirectUrl().equals("")){
			model.addAttribute("directUrl", cartvo.getDirectUrl());
		}
		model.addAttribute("cart", buycart);
		return "page/shopping/cart";
	}
	
	
	@RequestMapping("/clear.do")
	public String toClear(HttpSession session,Model model){
		BuyCart buycart = (BuyCart)session.getAttribute("cart");
		if(buycart!=null){
			buycart.deleteAll();
		}
		return "redirect:add.do";
	}
	
	@RequestMapping("/delete.do")
	public String toDelete(HttpSession session,CartVO cartvo,Model model){
		BuyCart buycart = (BuyCart)session.getAttribute("cart");
		if(buycart!=null){
			buycart.deleteBuyItem(cartvo.getProductid(),cartvo.getStyleid());;
		}else{
			buycart = new BuyCart();
			session.setAttribute("cart", buycart);
		}
		model.addAttribute("cart", buycart);
		return "redirect:add.do";
	}
	
	@RequestMapping("/update.do")
	public String toUpdate(HttpServletRequest request,HttpSession session,CartVO cartvo,Model model){
		BuyCart buycart = (BuyCart)session.getAttribute("cart");
		if(buycart!=null){
			setAmount(request,buycart);
		}else{
			buycart = new BuyCart();
			session.setAttribute("cart", buycart);
		}
		if(cartvo.getDirectUrl()!=null&&!cartvo.getDirectUrl().equals("")){
			model.addAttribute("directUrl", cartvo.getDirectUrl());
		}
		model.addAttribute("cart", buycart);
		return "redirect:add.do";
	}
	
	@RequestMapping("/settleaccount.do")
	public String settleAccounts(HttpServletRequest request,HttpSession session,CartVO cartvo,Model model){
		BuyCart buycart = (BuyCart)session.getAttribute("cart");
		if(buycart!=null){
			setAmount(request,buycart);
		}else{
			buycart = new BuyCart();
			session.setAttribute("cart", buycart);
		}
		model.addAttribute("cart", buycart);
		String url = "/customer/shopping/deliverinfoUI.do";
		if(cartvo.getDirectUrl()!=null&&!cartvo.getDirectUrl().equals("")){
			url = new String(Base64.decodeBase64(cartvo.getDirectUrl()));
		}
		model.addAttribute("directUrl", url);
		return "redirect:"+url;
	}
	
	private void setAmount(HttpServletRequest request,BuyCart buycart){
		for(BuyItem item : buycart.getItems()){
			StringBuffer key = new StringBuffer("amount_");
			key.append(item.getProduct().getId()).append("_").append(item.getProduct().getStyle().getId());
			String amountStr = request.getParameter(key.toString());
			if(amountStr!=null&&!amountStr.equals("")){
				try{
					int amount = Integer.parseInt(amountStr);
					if(amount>0) item.setAmount(amount);
				}catch(RuntimeException e){}
			}
		}
	}
	
	
	
}
