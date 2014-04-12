package com.huang.spring.example;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * TransactionSynchronizationManager负责管理当前线程的资源，
 * 资源可以主动绑定到TransactionSynchronizationManager中。
 *
 */
@SuppressWarnings("resource")
public class testSession {
	
	@Resource 
	private HelloDao helloDao;
	
	@Test
    public void testSave() {
		ApplicationContext ac = new FileSystemXmlApplicationContext("file:src/test/resources/spring.xml");  
        
        HelloDao helloDao = (HelloDao) ac.getBean("helloDao");  
		helloDao.saveHello();  
	}

}
