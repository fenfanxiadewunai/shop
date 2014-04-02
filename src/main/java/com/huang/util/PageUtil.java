package com.huang.util;
public class PageUtil {
    
    
    /**
     * Use the origin page to create a new page
     * @param page
     * @param totalRecords
     * @return
     */
    public static Pagenation createPage(Pagenation page, int totalRecords){
        return createPage(page.getPageSize(), page.getCurrentPage(), totalRecords);
    }
    
    /**  
     * the basic page utils not including exception handler
     * @param pageSize
     * @param currentPage
     * @param totalRecords
     * @return page
     */
    public static Pagenation createPage(int pageSize, int currentPage, int totalRecords){
        pageSize = getEveryPage(pageSize);
        currentPage = getCurrentPage(currentPage);
        int beginIndex = getBeginIndex(pageSize, currentPage);
        int totalPage = getTotalPage(pageSize, totalRecords);
        boolean hasNextPage = hasNextPage(currentPage, totalPage);
        boolean hasPrePage = hasPrePage(currentPage);
        
        return new Pagenation(hasPrePage, hasNextPage,  
            	 	    pageSize, totalPage, 
            	 	    currentPage, beginIndex,totalRecords);
    }
    
    private static int getEveryPage(int everyPage){
        return everyPage == 0 ? 10 : everyPage;
    }
    
    private static int getCurrentPage(int currentPage){
        return currentPage == 0 ? 1 : currentPage;
    }
    
    private static int getBeginIndex(int everyPage, int currentPage){
        return (currentPage - 1) * everyPage; 
    }
	
    private static int getTotalPage(int everyPage, int totalRecords){
        int totalPage = 0;
		
        if(totalRecords % everyPage == 0)
            totalPage = totalRecords / everyPage; 
        else
        	totalPage = totalRecords / everyPage + 1 ;
		
        return totalPage;
    }
    
    private static boolean hasPrePage(int currentPage){
        return currentPage == 1 ? false : true; 
    }
    
    private static boolean hasNextPage(int currentPage, int totalPage){
        return currentPage == totalPage || totalPage == 0 ? false : true;
    }
    

}
