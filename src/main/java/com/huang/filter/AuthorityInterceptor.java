package com.huang.filter;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.huang.domain.privilege.Employee;
import com.huang.domain.privilege.Permission;
import com.huang.domain.privilege.PrivilegeGroup;
import com.huang.domain.privilege.SystemPrivilege;
import com.huang.util.WebUtil;


public class AuthorityInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if(!request.getRequestURI().startsWith("/controller/"))return true;
		
		if(!validate(request, response, handler)){
			String message = Base64.encodeBase64String("您没有权限".getBytes());
			response.sendRedirect("/share/message.do?message="+message);
			return false;
		}
		return true;
	}
	

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	private boolean validate(HttpServletRequest request,HttpServletResponse response, Object handler){
		HandlerMethod handler2=(HandlerMethod) handler;
		Permission permission = handler2.getMethodAnnotation(Permission.class);
		if(null == permission){
			//没有声明权限,放行
			return true;
		}
		SystemPrivilege methodPrivilege = new SystemPrivilege(permission.module(), permission.privilege());
		Employee employee = WebUtil.getEmployee(request);
		for(PrivilegeGroup group: employee.getGroups()){
			if(privilegescontains(group.getPrivileges(),methodPrivilege)){
				return true;
			}
		}
		return false;
	}
	
	
	private boolean privilegescontains(Set<SystemPrivilege> privileges,SystemPrivilege target){
		for(SystemPrivilege privilege : privileges){
			if(privilege.systemprivilegeequals(target)) return true;
		}
		return false;
	}
	
	
}
