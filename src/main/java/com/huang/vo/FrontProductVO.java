package com.huang.vo;

import java.util.HashMap;
import java.util.Map;

public class FrontProductVO {
	
	private static HashMap<String,HashMap<String,String>> sortkey = new HashMap<String,HashMap<String,String>>();
	
	static{
		/** 按照销量降序排序 **/
		HashMap<String,String> temp1 = new HashMap<String,String>();
		temp1.put("key", "sellcount");
		temp1.put("order", "desc");
		sortkey.put("selldesc", temp1);
		
		/** 按照销售价格降序排序 **/
		HashMap<String,String> temp2 = new HashMap<String,String>();
		temp2.put("key", "sellprice");
		temp2.put("order", "desc");
		sortkey.put("sellpricedesc", temp2);
		
		/** 按照销售价格升序排序 **/
		HashMap<String,String> temp3 = new HashMap<String,String>();
		temp3.put("key", "sellprice");
		temp3.put("order", "asc");
		sortkey.put("sellpriceasc", temp3);
		
		/** 按照创建时间降序排序 **/
		HashMap<String,String> temp4 = new HashMap<String,String>();
		temp4.put("key", "createdate");
		temp4.put("order", "desc");
		sortkey.put("createdatedesc", temp4);
	}
	
	private String sex;
	private String sort;
	private Integer typeid;
	private String brandid;
	private String style;
	private Integer currentpage;
	
	private Integer productid;
	
	
	public String getSex() {
		if(sex==null)sex = "";
		String ret = "";
		switch (sex) {
		case "MAN":
		case "WOMEN":
		case "NONE":
			ret = sex;break;
		default:
			ret= "";break;
		}
		return ret;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSort() {
		if(sort==null||sort.equals(""))sort = "createdatedesc";
		String ret = "";
		switch (sort) {
		case "selldesc":
		case "sellpricedesc":
		case "sellpriceasc":
			ret = sort;break;
		default:
			ret= "createdatedesc";break;
		}
		return ret;
	}
	public void setSort(String sort) {
		this.sort=sort;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}
	public String getStyle() {
		if(style==null||style.equals(""))
			style= "image";
		String ret = "";
		switch (style) {
		case "text":
			ret = style;break;
		default:
			ret= "image";break;
		}
		return ret;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Integer getCurrentpage() {
		if(currentpage==null||currentpage.equals(""))
			currentpage= 1;
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	
	public Map<String,String> getSortParam(){
		return sortkey.get(this.getSort());
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	
	
	
}
