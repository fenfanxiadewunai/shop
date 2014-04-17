package com.huang.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.domain.BuyCart;
import com.huang.domain.book.OrderContactInfo;
import com.huang.domain.book.OrderDeliverInfo;
import com.huang.domain.user.Gender;
import com.huang.util.WebUtil;
import com.huang.vo.DeliverVO;



@Controller
@RequestMapping("/customer/shopping")
public class DeliverInfoController {
	
	@RequestMapping("/savedeliverinfo.do")
	public String saveDeliverInfo(HttpServletRequest request,Model model,DeliverVO dvo){
		BuyCart cart = WebUtil.getBuyCart(request);
		if(cart!=null){
			if(cart.getDeliverInfo()==null){
				OrderDeliverInfo deliverinfo = new OrderDeliverInfo(dvo);
				cart.setDeliverInfo(deliverinfo);
			}else{
				cart.getDeliverInfo().init(dvo);
			}
				
			cart.setBuyerIsrecipients(dvo.getBuyerIsrecipients());
			if(cart.getContactInfo()==null){
				OrderContactInfo contactinfo = new OrderContactInfo(dvo);
				cart.setContactInfo(contactinfo);
			}else{
				cart.getContactInfo().init(dvo);
			}
			if(!cart.getBuyerIsrecipients()){
				cart.getContactInfo().setEmail(WebUtil.getBuyer(request).getEmail());
			}
			
			String url = "/customer/shopping/paymentwayUI.do";
			if(dvo.getDirectUrl()!=null&&!dvo.getDirectUrl().equals("")){
				url = new String(Base64.decodeBase64(dvo.getDirectUrl()));
			}
			
			return "redirect:"+url;
		}
		return "redirect:/product/list.do";

	}
	
	@RequestMapping("/deliverinfoUI.do")
	public String toDeliverInfoUI(HttpServletRequest request,Model model,DeliverVO dvo){
		dvo.setGender(Gender.MAN);
		dvo.setBuyer_gender(Gender.MAN);
		dvo.setEmail(WebUtil.getBuyer(request).getEmail());
		dvo.setBuyerIsrecipients(true);
		
		/**  用于回显 **/
		BuyCart cart = WebUtil.getBuyCart(request);
		if(cart.getDeliverInfo()!=null){
			dvo.setRecipients(cart.getDeliverInfo().getRecipients());
			dvo.setGender(cart.getDeliverInfo().getGender());
			dvo.setAddress(cart.getDeliverInfo().getAddress());
			dvo.setPostcode(cart.getDeliverInfo().getPostcode());
			dvo.setTel(cart.getDeliverInfo().getTel());
			dvo.setMobile(cart.getDeliverInfo().getMobile());
			dvo.setEmail(cart.getDeliverInfo().getEmail());
		}
		
		if(cart.getBuyerIsrecipients()!=null) dvo.setBuyerIsrecipients(cart.getBuyerIsrecipients());
		
		if(cart.getContactInfo()!=null){
			dvo.setBuyer(cart.getContactInfo().getBuyerName());
			dvo.setBuyer_gender(cart.getContactInfo().getGender());
			dvo.setAddress(cart.getContactInfo().getAddress());
			dvo.setBuyer_postcode(cart.getContactInfo().getPostcode());
			dvo.setBuyer_tel(cart.getContactInfo().getTel());
			dvo.setBuyer_mobile(cart.getContactInfo().getMobile());
		}
		
		if(dvo.getDirectUrl()!=null&&!dvo.getDirectUrl().equals("")){
			model.addAttribute("directUrl", dvo.getDirectUrl());
		}
		
		model.addAttribute("deliverinfo", dvo);
		return "page/shopping/deliverInfo";
	}
	
	@RequestMapping("/paymentway.do")
	public String toPay(HttpServletRequest request,Model model,DeliverVO dvo){
		BuyCart cart = WebUtil.getBuyCart(request);
		OrderDeliverInfo deliverinfo = new OrderDeliverInfo(dvo);
		cart.setDeliverInfo(deliverinfo);
		cart.setBuyerIsrecipients(dvo.getBuyerIsrecipients());
		OrderContactInfo contactinfo = new OrderContactInfo();
		if(!cart.getBuyerIsrecipients()){
			contactinfo.setEmail(WebUtil.getBuyer(request).getEmail());
		}
		return "page/shopping/deliverInfo";
	}

}
