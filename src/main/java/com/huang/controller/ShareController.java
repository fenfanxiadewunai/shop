package com.huang.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.vo.MessageVO;


@Controller
@RequestMapping("/share")
public class ShareController {

	@RequestMapping("/message.do")
	public String toMessage(MessageVO mvo,Model model){
		String message = new String(Base64.decodeBase64(mvo.getMessage()));
		model.addAttribute("message", message);
		model.addAttribute("callback", "/product/list.do");
		return "page/share/message";
	}
}
