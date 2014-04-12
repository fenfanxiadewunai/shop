package com.huang.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.domain.ProductInfo;
import com.huang.service.ProductService;
import com.huang.util.WebUtil;
import com.huang.vo.FrontProductVO;


@Controller
@RequestMapping("/productselect")
public class ProductSelectController {
	
	@Resource
	private ProductService productService;
	
	/**
	 * 根据typeid 获取该类别下地top sell 商品
	 * @param model
	 * @param pvo
	 * @param response
	 */
	@RequestMapping("/topsell.do")
	public String toTopSell(Model model,FrontProductVO pvo){
		List<ProductInfo> ret = productService.getTopSellProduct(pvo.getTypeid(),4);
		model.addAttribute("topresult", ret);
		return "page/product/front/topsell";
	}
	
	@RequestMapping("/viewhistory.do")
	public String toViewHistory(Model model,HttpServletRequest request){
		String cookieValue = WebUtil.getCookieByName(request, "productViewHistory");
		if(cookieValue!=null&&!cookieValue.equals("")){
			String[] ids = cookieValue.split("-");
			List<Integer> productids = new ArrayList<Integer>();
			for(int i = 0;i<ids.length;i++){
				productids.add(Integer.parseInt(ids[i].trim()));
			}
			List<ProductInfo> productret = productService.getListProductInfoByIds(productids, 10);
			model.addAttribute("viewHistory", productret);
		}
		return "page/product/front/viewhistory";
	}
	
	@RequestMapping("/viewimage.do")
	public String toViewImage(Model model,String imagepath){
		model.addAttribute("imagepath", imagepath);
		return "page/product/front/showimage";
	}
	
}
