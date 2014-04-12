package com.huang.spring.example;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class MySessionFactory {
	
	 /** 
     * 如果当前线程存在MySession，就使用该MySession，否者开启一个新的MySession 
     * @return 
     */  
    public MySession getSession(){  
        //传入this，是因为，我们以当前factory类作为键保存的MySession  
        if(TransactionSynchronizationManager.hasResource(this)){  
            return getCurrentSession();  
        }else{  
            return openSession();  
        }  
          
    } 
	
	/** 
     * 获取当前线程的MySession 
     * @return 
     */  
    private MySession getCurrentSession() {  
        MySession mySession = (MySession) TransactionSynchronizationManager.getResource(this);  
        return mySession;  
    }  
	
	public MySession openSession(){
		MySession mySession = new MySession();  
        mySession.setSessionId(System.currentTimeMillis());  
          
        //注册进当前线程管理一个Synchronization  
        TransactionSynchronization transactionSynchronization = new MyTransactionSynchronizationAdapter(this);  
        TransactionSynchronizationManager.registerSynchronization(transactionSynchronization);  
          
        //绑定新开启的一个MySession进当前线程事务管理器  
        TransactionSynchronizationManager.bindResource(this, mySession);  
          
        return mySession;
	}
}
