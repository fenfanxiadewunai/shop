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

import com.huang.domain.privilege.Employee;
import com.huang.util.WebUtil;

public class PrivilegeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Employee employee = WebUtil.getEmployee(req);
		if(employee == null){
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendRedirect("/employee/login.do");
		}else{
			chain.doFilter(request,response);
		}
	}

	@Override
	public void destroy() {
		
	}

}
