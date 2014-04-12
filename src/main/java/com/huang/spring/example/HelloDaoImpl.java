package com.huang.spring.example;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class HelloDaoImpl extends JdbcDaoSupport implements HelloDao{
	
	private MySessionFactory mySessionFactory;
	
	public void setMySessionFactory(MySessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}

	@Override
	public void saveHello() {
			mySessionFactory.getSession().save(null);
			this.getJdbcTemplate().execute("select * from user");
	}

}
