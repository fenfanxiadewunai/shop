package com.huang.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.domain.BuyCart;
import com.huang.domain.book.DeliverWay;
import com.huang.domain.book.PaymentWay;
import com.huang.util.WebUtil;
import com.huang.vo.DeliverVO;


@Controller
@RequestMapping("/customer/shopping")
public class PaymentWayController {
	
	@RequestMapping("/paymentwayUI.do")
	public String toPaymentwayUI(HttpServletRequest request,Model model,DeliverVO dvo){
		dvo.setDeliverway(DeliverWay.EXPRESSDELIVERY);
		dvo.setPaymentway(PaymentWay.NET);
		BuyCart cart = WebUtil.getBuyCart(request);
		if(cart.getDeliverInfo()==null){
			return "redirect:/customer/shopping/deliverinfoUI.do";
		}
		if(cart.getPaymentWay()!=null){
			dvo.setPaymentway(cart.getPaymentWay());
		}
		if(cart.getDeliverInfo().getDeliverWay()!=null){
			dvo.setDeliverway(cart.getDeliverInfo().getDeliverWay());
		}
		if(cart.getDeliverInfo().getRequirement()!=null){
			List<String> contents = Arrays.asList("工作日、双休日与假日均可送货","只双休日、假日送货","只工作日送货(双休日、假日不用送)","学校地址/地址白天没人，请尽量安排其他时间送货");
			if(contents.contains(cart.getDeliverInfo().getRequirement())){
				dvo.setRequirement(cart.getDeliverInfo().getRequirement());
			}else{
				dvo.setRequirement("other");
				dvo.setDelivernote(cart.getDeliverInfo().getRequirement());
			}
		}
		
		model.addAttribute("deliverinfo", dvo);
		return "page/shopping/paymentway";

	}
	
	@RequestMapping("/paymentsave.do")
	public String toSavePaymentway(HttpServletRequest request,Model model,DeliverVO dvo){
		BuyCart cart = WebUtil.getBuyCart(request);
		cart.getDeliverInfo().setDeliverWay(dvo.getDeliverway());
		cart.setPaymentWay(dvo.getPaymentway());
		if(DeliverWay.EXPRESSDELIVERY.equals(dvo.getDeliverway())||DeliverWay.EXIGENCEEXPRESSDELIVERY.equals(dvo.getDeliverway())){
			if("other".equals(dvo.getRequirement())){
				cart.getDeliverInfo().setRequirement(dvo.getDelivernote());
			}else{
				cart.getDeliverInfo().setRequirement(dvo.getRequirement());
			}
		}else{
			cart.getDeliverInfo().setRequirement(null);
		}
		return "redirect:/customer/shopping/confirm.do";

	}
	
	@RequestMapping("/confirm.do")
	public String toConfirm(HttpServletRequest request,Model model){
		BuyCart cart = WebUtil.getBuyCart(request);
		String url="/customer/shopping/confirm.do";
		model.addAttribute("directUrl", new String(Base64.encodeBase64(url.getBytes())));
		model.addAttribute("cart", cart);
		return "page/shopping/confirm";
	}

}
