package com.huang.spring.example;

import java.io.Serializable;

public class MySession {
	private Long sessionId;
	
	public void save(Serializable object){
		System.out.println(sessionId+":save");
	}
	
	public void beginTransaction(){
		System.out.println(sessionId+":beginTransaction");
	}
	
	public void commit(){
		System.out.println(sessionId+":commit");
	}
	
	public void rollback(){
		System.out.println(sessionId+":rollback");
	}
	
	public Long getSessionId(){
		return sessionId;
	}
	
	public void setSessionId(Long sessionId){
		this.sessionId = sessionId;
	}
	
	@Override
	public String toString() {
		return "MySession [sessionId=" + sessionId + "]";  
	}
}
