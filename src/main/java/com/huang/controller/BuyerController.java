package com.huang.controller;

import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.domain.user.Buyer;
import com.huang.mail.EmailSender;
import com.huang.service.BuyerService;
import com.huang.util.MD5;
import com.huang.util.VelocityUtil;
import com.huang.vo.UserVO;


@Controller
@RequestMapping("/user")
public class BuyerController {
	
	@Resource
	private BuyerService buyerService;
	
	@Resource
	private VelocityUtil velocityUtil;
	
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
	
	@RequestMapping("/findpasswordUI.do")
	public String toFindPasswordUI(){
		return "page/user/findPassword";
	}
	
	/**
	 * 
	 *找回密码之发送邮件
	 */
	@RequestMapping("/getpassword.do")
	public String toGetpassword(Model model,String username) throws Throwable{
		if(username!=null && !"".equals(username)){
			if(buyerService.exist(username)){
				Buyer buyer = buyerService.getWithPassword(username);
				Template template = velocityUtil.getVelocityEngine().getTemplate("page/template/mailContent.html");
				VelocityContext context = new VelocityContext();
				context.put("username", buyer.getUsername());
				context.put("validateCode", MD5.MD5Encode(buyer.getUsername()+ buyer.getPassword()));
				StringWriter writer = new StringWriter(); 
				template.merge(context, writer);
				String content = writer.toString();
				EmailSender.send(buyer.getEmail(), "随心购--找回密码邮件", content, "text/html");
				return "page/user/findPassword2";
			}else{
				model.addAttribute("message", "用户不存在");
			}			
		}else{
			model.addAttribute("message", "请输入用户名");
		}
		return "page/user/findPassword";
	}
	

	
	/**
	 * 找回密码之显示密码修改界面
	 */
	@RequestMapping("/updateUI.do")
	public String toUpdatePassword(Model model,String username,String validateCode){
		if(username!=null && !"".equals(username)){
			if(buyerService.exist(username)){
				Buyer buyer = buyerService.getWithPassword(username);
				String code = MD5.MD5Encode(buyer.getUsername()+ buyer.getPassword());
				if(code.equals(validateCode)){//校验通过，表示来源合法
					model.addAttribute("username", username);
					model.addAttribute("validateCode", validateCode);
					return "page/user/findPassword3";
				}
			}			
		}
		return "page/user/errorresult";
	}
	
	
	/**
	 * 找回密码之修改密码
	 */
	@RequestMapping("/updatepassword.do")
	public String toChangePassword(Model model,String username,String validateCode,String password){
		if(username!=null && !"".equals(username)){
			if(buyerService.exist(username)){
				Buyer buyer = buyerService.getWithPassword(username);
				String code = MD5.MD5Encode(buyer.getUsername()+ buyer.getPassword());
				if(code.equals(validateCode)){//校验通过，表示来源合法					
					buyerService.updatePassword(buyer.getUsername(), password);
					model.addAttribute("message", "密码修改成功");
					return "page/share/message";
				}
			}			
		}
		return "page/user/errorresult";
	}
	

}
