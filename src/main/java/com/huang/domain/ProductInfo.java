package com.huang.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.huang.vo.ProductVO;

public class ProductInfo implements Serializable{
	
	private static final long serialVersionUID = 4047808530311665033L;

	private int id;
	
	/**  货号 **/
	private String code;
	/**  产品名称 **/
	private String name;
	/** 品牌 **/
	private Brand brand;
	/**  型号 **/
	private String model;
	/**  采购价  **/
	private float baseprice;
	/**  市场价  **/
	private float marketprice;
	/**  销售价  **/
	private float sellprice;
	/**  重量，单位：克  **/
	private int weight;
	/**  产品简介  **/
	private String description;
	/**  购买说明  **/
	private String buyexplain;
	/**  是否可见  **/
	private boolean visible = true;
	/**  产品类别  **/
	private ProductType type;
	/**  上架日期  **/
	private Date createdate = new Date();
	/**  人气指数  **/
	private int clickcount=1;
	/**  销售量  **/
	private int sellcount=0;
	/**  是否推荐  **/
	private boolean commend=false;
	/**  性别要求  **/
	private Sex sexrequest = Sex.NONE;
	/**  产品样式  **/
	private List<ProductStyle> styles = new ArrayList<ProductStyle>();
	
	public ProductInfo(){}
	
	public ProductInfo(Integer id){
		this.id = id;
	}
	
	public ProductInfo(ProductVO productVO){
		this.setName(productVO.getName());
		this.setBaseprice(productVO.getBaseprice());
		this.setSellprice(productVO.getSellprice());
		this.setMarketprice(productVO.getMarketprice());
		this.setBrand(new Brand(productVO.getBrandid()));
		this.setBuyexplain(productVO.getBuyexplain());
		this.setCode(productVO.getCode());
		this.setDescription(productVO.getDescription());
		this.setModel(productVO.getModel());
		this.setSexrequest(Sex.valueOf(productVO.getSex()));
		this.setWeight(productVO.getWeight());
		this.setType(new ProductType(productVO.getTypeid()));
	}
	
	public void addProductStyle(ProductStyle style){
		if(!this.styles.contains(style)){
			this.styles.add(style);
			style.setProduct(this);
		}
	}
	
	public void removeProductStyle(ProductStyle style){
		if(this.styles.contains(style)){
			this.styles.remove(style);
			style.setProduct(null);
		}
	}
	
	public List<ProductStyle> getStyles() {
		return styles;
	}

	public void setStyles(List<ProductStyle> styles) {
		this.styles = styles;
	}

	public int getStyleCount(){
		return styles.size();
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public float getBaseprice() {
		return baseprice;
	}
	public void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}
	public float getMarketprice() {
		return marketprice;
	}
	public void setMarketprice(float marketprice) {
		this.marketprice = marketprice;
	}
	public float getSellprice() {
		return sellprice;
	}
	public void setSellprice(float sellprice) {
		this.sellprice = sellprice;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBuyexplain() {
		return buyexplain;
	}
	public void setBuyexplain(String buyexplain) {
		this.buyexplain = buyexplain;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getClickcount() {
		return clickcount;
	}
	public void setClickcount(int clickcount) {
		this.clickcount = clickcount;
	}
	public int getSellcount() {
		return sellcount;
	}
	public void setSellcount(int sellcount) {
		this.sellcount = sellcount;
	}
	public boolean isCommend() {
		return commend;
	}
	public void setCommend(boolean commend) {
		this.commend = commend;
	}
	public Sex getSexrequest() {
		return sexrequest;
	}
	public void setSexrequest(Sex sexrequest) {
		this.sexrequest = sexrequest;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ProductInfo [id=" + id + ", code=" + code + ", name=" + name
				+ ", brand=" + brand + ", model=" + model + ", baseprice="
				+ baseprice + ", marketprice=" + marketprice + ", sellprice="
				+ sellprice + ", weight=" + weight + ", description="
				+ description + ", buyexplain=" + buyexplain + ", visible="
				+ visible + ", type=" + type + ", createdate=" + createdate
				+ ", clickcount=" + clickcount + ", sellcount=" + sellcount
				+ ", commend=" + commend + ", sexrequest=" + sexrequest
				+ ", styles=" + styles + "]";
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
		ProductInfo other = (ProductInfo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public void setProductVO(ProductVO productVO){
		this.setName(productVO.getName());
		this.setBaseprice(productVO.getBaseprice());
		this.setSellprice(productVO.getSellprice());
		this.setMarketprice(productVO.getMarketprice());
		this.setBrand(new Brand(productVO.getBrandid()));
		this.setBuyexplain(productVO.getBuyexplain());
		this.setCode(productVO.getCode());
		this.setDescription(productVO.getDescription());
		this.setModel(productVO.getModel());
		this.setSexrequest(Sex.valueOf(productVO.getSex()));
		this.setWeight(productVO.getWeight());
		this.setType(new ProductType(productVO.getTypeid()));
	}
	
	public float savedPrice(){
		return marketprice-sellprice;
	}
	
	public ProductStyle getStyle() {
		if(styles.size()>0) return styles.get(0);
		return null;
	}
	
	public String getSimpleDesc(){
		if(description.length()>200)
		return description.substring(0, 200);
		else return description;
	}

}
