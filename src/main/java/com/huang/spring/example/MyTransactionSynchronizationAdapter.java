package com.huang.spring.example;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 核心事务同步适配器<br/> 
 * 当方法上面定义了@Transactional注解，那么当每次状态发生时就会调用本同步适配器 
 * @author fenfanxiadewunai
 *
 */
public class MyTransactionSynchronizationAdapter extends TransactionSynchronizationAdapter{
	
	private MySessionFactory mySessionFactory;
	
	public MyTransactionSynchronizationAdapter(MySessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	
	@Override
	public void beforeCommit(boolean readOnly) {
		//readOnly标识是否是一个只读线程
		if(!readOnly){
			MySession mySession = (MySession)TransactionSynchronizationManager.getResource(mySessionFactory);
			mySession.beginTransaction();
		}
	}
	
	@Override  
    public void afterCompletion(int status) {  
        MySession mySession = (MySession) TransactionSynchronizationManager.getResource(mySessionFactory);  
        if (STATUS_COMMITTED == status) {  
            mySession.commit();  
        }  
        //当然，你还可以定义回滚方法  
    } 
	
}
