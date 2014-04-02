package com.huang.util;

public class Pagenation {

	/** 是否有上一页 */
	private boolean hasPrePage;

	/** 是否有下一页 */
	private boolean hasNextPage;

	/** 每页记录数 */
	private int pageSize;

	/** 总页数 */
	private int totalPage;

	/** 当前页 数 */
	private int currentPage;

	/** 查询的开始行数 */
	private int beginIndex;
	
	private int totalCount;
	
	

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Pagenation() {

	}

	/**
	 * construct the page by everyPage
	 * 
	 * @param everyPage
	 * */

	public Pagenation(int everyPage) {
		this.pageSize = everyPage;
	}

	/** The whole constructor */
	public Pagenation(boolean hasPrePage, boolean hasNextPage, int everyPage,
			int totalPage, int currentPage, int beginIndex,int totalCount) {
		this.hasPrePage = hasPrePage;
		this.hasNextPage = hasNextPage;
		this.pageSize = everyPage;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.beginIndex = beginIndex;
		this.totalCount = totalCount;
	}

	/**
	 * @return Returns the beginIndex.
	 */
	public int getBeginIndex() {
		return beginIndex;
	}

	/**
	 * @param beginIndex
	 *            The beginIndex to set.
	 */
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	/**
	 * @return Returns the currentPage.
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            The currentPage to set.
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return Returns the hasNextPage.
	 */
	public boolean getHasNextPage() {
		return hasNextPage;
	}

	/**
	 * @param hasNextPage
	 *            The hasNextPage to set.
	 */
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	/**
	 * @return Returns the hasPrePage.
	 */
	public boolean getHasPrePage() {
		return hasPrePage;
	}

	/**
	 * @param hasPrePage
	 *            The hasPrePage to set.
	 */
	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	/**
	 * @return Returns the totalPage.
	 * 
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage
	 *            The totalPage to set.
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getNextPage(){
		return this.currentPage+1;
	}
	
	public int getPrePage(){
		return this.currentPage-1;
	}

}
