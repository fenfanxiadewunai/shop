package com.huang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.huang.domain.user.Buyer;
import com.huang.util.WebUtil;

public class BuyerLoginValidateFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		Buyer buyer = WebUtil.getBuyer(req);
		if(buyer==null){
			String url = WebUtil.getRequestURIWithParam(req);
			String directUrl = new String(Base64.encodeBase64(url.getBytes()));
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendRedirect("/user/loginUI.do?="+directUrl);
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

}
