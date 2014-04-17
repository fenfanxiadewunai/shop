package com.huang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.domain.user.Buyer;
import com.huang.service.BuyerService;
import com.huang.vo.UserVO;


@Controller
@RequestMapping("/user")
public class BuyerController {
	
	@Resource
	private BuyerService buyerService;
	
	@RequestMapping("/reg.do")
	public String toReg(Model model,UserVO uvo){
		if(buyerService.exist(uvo.getUsername())){
			model.addAttribute("error", "该用户名已经存在");
			return "page/user/userReg";
		}else{
			Buyer buy = new Buyer(uvo);
			buyerService.add(buy);
			model.addAttribute("message", "用户注册成功");
			String url = "/product/list.do";
			if(uvo.getDirectUrl()!=null)url = uvo.getDirectUrl();
			model.addAttribute("callback", url);
			return "page/share/message";
		}
		
	}
	
	@RequestMapping("/login.do")
	public String toLogIn(Model model,UserVO uvo,HttpSession session){
		if(checkUserNameAndPassword(uvo.getUsername(),uvo.getPassword())){
			if(buyerService.checkUser(uvo.getUsername(), uvo.getPassword())){
				session.setAttribute("user", buyerService.get(uvo.getUsername()));
				String url = "/customer/shopping/deliverinfoUI.do";
				if(uvo.getDirectUrl()!=null&&!uvo.getDirectUrl().equals("")) url = uvo.getDirectUrl();
				return "redirect:"+url;
			}else{
				model.addAttribute("error", "用户名或密码有误");
				return "page/user/login";
			}
		}
		return "page/user/login";
		
	}
	
	@RequestMapping("/loginUI.do")
	public String toLogInUI(){
		return "page/user/login";
	}
	
	private boolean checkUserNameAndPassword(String username,String password){
		if(username!=null&&!username.trim().equals("")&&password!=null&&!password.trim().equals(""))return true;
		return false;
	}
	
	
	
	@RequestMapping("/regUI.do")
	public String toRegUI(){
		return "page/user/userReg";
	}
	
	@RequestMapping("/checkexist.do")
	public String toTopSell(Model model,String username){
		boolean exist = buyerService.exist(username);
		model.addAttribute("exist", exist);
		return "page/user/checkuser";
	}
	

}
