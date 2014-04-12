package com.huang.controller;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.domain.ProductInfo;
import com.huang.domain.ProductType;
import com.huang.service.BrandService;
import com.huang.service.ProductService;
import com.huang.service.ProductTypeService;
import com.huang.util.PageUtil;
import com.huang.util.Pagenation;
import com.huang.vo.FrontProductVO;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/product")
public class FrontProductController {
	
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private BrandService brandService;
	
	/**
	 * 默认情况下,按照创建时间
	 * @param model
	 * @param pvo
	 * @return
	 */

	@RequestMapping("/list.do")
	public String toList(Model model,FrontProductVO pvo){
		int pageSize = 6;
		int cPage = pvo.getCurrentpage();
		int totalCount = 0;
		Pagenation page = null;
		List<ProductInfo> result = null;
		
		HashMap<String,Object> map = createQueryParam(pvo);
		totalCount = productService.countByDynamic(map);
		page = PageUtil.createPage(pageSize, cPage, totalCount);
		map.put("pageSize", page.getPageSize());
		map.put("pageOffset", page.getBeginIndex());
		result = productService.findByDynamic(map);
		
		if(pvo.getTypeid()!=null&&pvo.getTypeid()>0){
			List<ProductType> parents = productTypeService.getParentTypes(pvo.getTypeid());
			model.addAttribute("typeitself", productTypeService.getById(pvo.getTypeid()));
			model.addAttribute("menutypes", parents);
			model.addAttribute("brands", productService.getBrandsByProductTypeIds((List<Integer>)map.get("typeids")));
			model.addAttribute("subtypes", productTypeService.getFirstSubTypes(pvo.getTypeid()));
		}else{
			model.addAttribute("brands", productService.getBrandsByProductTypeIds(null));
			model.addAttribute("subtypes", productTypeService.getFirstSubTypes(null));
		}
		
		model.addAttribute("result", result);
		model.addAttribute("page", page);
		model.addAttribute("pvo", pvo);
		
		if(pvo.getStyle().equals("image")){
			return "page/product/front/productimagelist";
		}else{
			return "page/product/front/producttextlist";
		}

	}
	
	private HashMap<String,Object> createQueryParam(FrontProductVO pvo){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("visible", true);
		if(pvo.getTypeid()!=null&&pvo.getTypeid()>0){
			List<Integer> ret = productTypeService.getAllSubTypeids(pvo.getTypeid());
			map.put("typeids", ret);
		}
		if(pvo.getBrandid()!=null&&!pvo.getBrandid().equals("")){
			map.put("brandid", pvo.getBrandid());
		}
		if(!pvo.getSex().equals("")){
			map.put("sexrequest", pvo.getSex());
		}
		map.putAll(pvo.getSortParam());
		return map;
	}
	

}
