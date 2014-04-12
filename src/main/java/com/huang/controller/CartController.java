package com.huang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.huang.domain.BuyCart;
import com.huang.domain.BuyItem;
import com.huang.domain.ProductInfo;
import com.huang.domain.ProductStyle;
import com.huang.domain.User;
import com.huang.service.ProductService;
import com.huang.vo.CartVO;


@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Resource
	private ProductService productService;
	
	@RequestMapping("/add.do")
	public String toAdd(HttpSession session,CartVO cartvo,Model model){
		BuyCart buycart = (BuyCart)session.getAttribute("cart");
		if(buycart == null){
			buycart = new BuyCart();
			session.setAttribute("cart", buycart);
		}
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
		model.addAttribute("cart", buycart);
		return "page/shopping/cart";
		
	}
	
	@RequestMapping("/hello1.do")
	public String test(@ModelAttribute("cart")User user ){
		System.out.println(user);
		user.setUserName("zhang");
		System.out.println(user);
		return "page/test/out";
	}
	
	@RequestMapping("/hello.do")
	public String hello(HttpSession session){
		if(session.getAttribute("cart")==null){
			session.setAttribute("cart", new User("huang", 25));
		}
		return "page/test/out";
	}
	
	
}
