package com.huang.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener{
	
	private static Map<String,HttpSession> sessions = new HashMap<String,HttpSession>();

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		System.out.println(sessionEvent.getSession().getId());
		sessions.put(sessionEvent.getSession().getId(), sessionEvent.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		System.out.println("remove sessionId:"+sessionEvent.getSession().getId());
		sessions.remove(sessionEvent.getSession().getId());
	}
	
	public static HttpSession getSession(String sessionId){
		return sessions.get(sessionId);
	}

}
