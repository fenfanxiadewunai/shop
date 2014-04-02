package com.huang.domain;

import java.io.Serializable;
import java.util.Date;

public class UploadFile implements Serializable{
	
	private static final long serialVersionUID = 3969223127401647413L;
	private int id;
	private String filepath;
	private Date uploadtime = new Date();
	
	public UploadFile(){}
	
	public UploadFile(String filepath){
		this.filepath = filepath;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Date getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UploadFile other = (UploadFile) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UploadFile [id=" + id + ", filepath=" + filepath
				+ ", uploadtime=" + uploadtime + "]";
	}
	
	
	
	
}
