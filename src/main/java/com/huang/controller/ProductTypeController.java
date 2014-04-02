package com.huang.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.domain.ProductType;
import com.huang.service.ProductTypeService;
import com.huang.util.PageUtil;
import com.huang.util.Pagenation;

@Controller
@RequestMapping("/producttype")
public class ProductTypeController {
	
	
	@Resource
	private ProductTypeService productTypeService;
	
	/**
	 * 传递当前页，以及父类parentid
	 * 默认情况下，当前页为1
	 * @param model
	 * @param currentPage
	 * @param parentid
	 * @return
	 */
	@RequestMapping("/list.do")
	public String toList(Model model,String currentPage,String parentid,String name,String query){
		int pageSize = 3;
		if(currentPage==null||currentPage.equals(""))
			currentPage= "1";
		int cPage = Integer.parseInt(currentPage);
		
		int totalCount = 0;
		
		List<ProductType> result = null;
		Pagenation page = null;
		
		if("true".equals(query)){
			totalCount = productTypeService.countByName(name);
			page = PageUtil.createPage(pageSize, cPage, totalCount);
			result = productTypeService.findByName(name, page.getPageSize(), page.getBeginIndex());
			model.addAttribute("name", name);
			model.addAttribute("query", query);
		}
		else{
			totalCount = productTypeService.countwithparentid(parentid);
			page = PageUtil.createPage(pageSize, cPage, totalCount);
			result = productTypeService.findwithChild(page.getPageSize(), page.getBeginIndex(),parentid);
			model.addAttribute("parentid", parentid);
		}
		
		model.addAttribute("result", result);
		model.addAttribute("page", page);

		return "page/product/producttypelist";
	}
	
	
	/**
	 * 添加界面导航，需要添加parentid
	 * @param model
	 * @param parentid
	 * @return
	 */
	@RequestMapping("/addUI.do")
	public String toAddUI(Model model,String parentid){
		model.addAttribute("parentid", parentid);
		return "page/product/add_productType";
	}
	
	
	/**
	 * productType添加功能,根据接收到的parentid、name以及note保存于数据库中。
	 * @param model
	 * @param parentid
	 * @param name
	 * @param note
	 * @return
	 */
	@RequestMapping("/add.do")
	public String toAdd(Model model,String parentid,String name,String note){
		ProductType product = new ProductType(name,note);
		if(parentid!=null&&!parentid.equals(""))
			product.setParent(new ProductType(parentid));
		
		productTypeService.add(product);
		
		model.addAttribute("message", "添加成功");
		
		return "page/share/message";
	}
	
	@RequestMapping("/editUI.do")
	public String toEditUI(Model model,String typeid){
		ProductType product = productTypeService.getById(Integer.parseInt(typeid));
		model.addAttribute("product", product);
		return "page/product/edit_productType";
	}
	
	@RequestMapping("/edit.do")
	public String toEdit(Model model,String typeid,String name,String note){
		ProductType product = new ProductType(Integer.parseInt(typeid),name,note);
		productTypeService.update(product);
		
		model.addAttribute("message", "修改成功");
		return "page/share/message";
	}
	
	@RequestMapping("/queryUI.do")
	public String toQueryUI(Model model,String typeid){
		return "page/product/query_productType";
	}
	
}
