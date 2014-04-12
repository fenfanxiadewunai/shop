package com.huang.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.domain.ProductInfo;
import com.huang.domain.ProductType;
import com.huang.service.ProductService;
import com.huang.service.ProductTypeService;
import com.huang.util.WebUtil;
import com.huang.vo.FrontProductVO;


@Controller
@RequestMapping("/viewproduct")
public class ViewProductInfoController {

	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	
	@RequestMapping("/product.do")
	public String toInfo(Model model,HttpServletRequest request,HttpServletResponse response,FrontProductVO pvo){
		ProductInfo product = productService.getByIdWithProductStyle(pvo.getProductid());
		
		if(product==null){
			model.addAttribute("message", "获取不到您需要浏览的产品");
			model.addAttribute("callback", "http://localhost:8080/shop/list.do");
			return "page/share/message";
		}
		
		List<ProductType> parents = productTypeService.getParentTypes(product.getType().getTypeid());
		model.addAttribute("menutypes", parents);
		
		WebUtil.addCookie(response, "productViewHistory", buildViewHistory(request, pvo.getProductid()), 30*24*60*60);
		model.addAttribute("product", product);
		return "page/product/front/productview";
	}
	
	private String buildViewHistory(HttpServletRequest request,Integer productid){
		String cookieValue = WebUtil.getCookieByName(request, "productViewHistory");
		LinkedList<Integer> productids = new LinkedList<Integer>();
		if(cookieValue!=null&&!cookieValue.trim().equals("")){
			String[] ids = cookieValue.split("-");
			for(String id:ids){
				productids.offer(new Integer(id.trim()));
			}
			if(productids.contains(productid))productids.remove(productid);
			if(productids.size()>=10) productids.poll();
		}
		productids.offer(productid);
		
		StringBuffer out = new StringBuffer();
		if(productids.size()>0){
			out.append(productids.get(0));
		}
		for(int i = 1;i<productids.size();i++){
			out.append("-").append(productids.get(i));
		}
		return out.toString();
		
	}
	
}
