package com.huang.dao;

import java.util.List;

import com.huang.domain.UploadFile;

public interface UploadFileDao {
	
	public void add(UploadFile file);

	public UploadFile getById(int id);
	
	public List<UploadFile> find(int pageSize,int pageOffset);
	
	public int count();
	
	public void update(UploadFile file);
	
	public void batchdelete(List<Integer> list);
	
	public List<UploadFile> batchselect(List<Integer> list);
}
