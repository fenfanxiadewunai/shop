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

import com.huang.domain.BuyCart;
import com.huang.util.WebUtil;

public class BuyCartValidateFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		BuyCart cart = WebUtil.getBuyCart(req);
		if(cart==null|| cart.getItems().isEmpty()){
			HttpServletResponse resp = (HttpServletResponse)response;
			String message = Base64.encodeBase64String("目前，购物车中没有商品".getBytes());
			resp.sendRedirect("/share/message.do?message="+message);
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

}
