package com.huang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.UploadFileDao;
import com.huang.domain.UploadFile;
import com.huang.service.UploadFileService;

@Repository("uploadFileService")
public class UploadFileServiceImpl implements UploadFileService{

	@Resource
	private UploadFileDao uploadFileDao;
	
	@Override
	public void add(UploadFile file) {
		uploadFileDao.add(file);
	}

	@Override
	public UploadFile getById(int id) {
		return uploadFileDao.getById(id);
	}

	@Override
	public List<UploadFile> find(int pageSize, int pageOffset) {
		return uploadFileDao.find(pageSize, pageOffset);
	}

	@Override
	public int count() {
		return uploadFileDao.count();
	}

	@Override
	public void update(UploadFile file) {
		uploadFileDao.update(file);
	}

	@Override
	public void batchdelete(List<Integer> list) {
		uploadFileDao.batchdelete(list);
	}

	@Override
	public List<UploadFile> batchselect(List<Integer> list) {
		return uploadFileDao.batchselect(list);
	}

}
