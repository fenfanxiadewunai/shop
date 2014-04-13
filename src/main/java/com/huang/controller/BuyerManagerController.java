package com.huang.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.huang.domain.user.Buyer;
import com.huang.service.BuyerService;
import com.huang.util.PageUtil;
import com.huang.util.Pagenation;


@Controller
@RequestMapping("/controller/user")
public class BuyerManagerController {
	
	@Resource
	private BuyerService buyerService;

	@RequestMapping("/list.do")
	public String toList(Model model, String currentPage) {
		int pageSize = 3;
		if (currentPage == null || currentPage.equals(""))
			currentPage = "1";
		int cPage = Integer.parseInt(currentPage);

		int totalCount = buyerService.countAll();
		Pagenation page = PageUtil.createPage(pageSize, cPage, totalCount);
		List<Buyer> result = buyerService.find(page.getPageSize(), page.getBeginIndex());
		model.addAttribute("result", result);
		model.addAttribute("page", page);

		return "page/user/userlist";
	}
	
	@RequestMapping("/queryUI.do")
	public String toQueryUI(Model model, String typeid) {
		return "page/product/query_brand";
	}
	
	@RequestMapping("/visible.do")
	public String toSetVisible(Model model,String[] usernames){
		if(usernames.length>0){
			buyerService.setVisibleStatus(usernames, true);
		}
		return "redirect:list.do";
	}
	
	@RequestMapping("/disvisible.do")
	public String toSetDisVisible(Model model,String[] usernames){
		if(usernames.length>0){
			buyerService.setVisibleStatus(usernames, false);
		}
		return "redirect:list.do";
	}
}
