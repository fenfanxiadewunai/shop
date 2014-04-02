package com.huang.domain;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.service.UploadFileService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"}) 
public class testUploadFile {
	
	@Resource 
	private UploadFileService uploadFileService;

	@Test
    public void testAdd() {
		UploadFile uf = new UploadFile("huang.jpg");
		uploadFileService.add(uf);
    }
	
	@Test
    public void testGetById() {
		UploadFile file = uploadFileService.getById(1);
		System.out.println(file);
    }
	
	@Test
    public void testBatchGet() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		ids.add(4);
		
		List<UploadFile> ret = uploadFileService.batchselect(ids);
		for(UploadFile fi : ret){
			System.out.println(fi);
		}
    }
	
	@Test
    public void testBatchDelete() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		ids.add(4);
		
		uploadFileService.batchdelete(ids);
		
		List<UploadFile> ret = uploadFileService.batchselect(ids);
		System.out.println(ret.size());
		
    }
	
	

}
