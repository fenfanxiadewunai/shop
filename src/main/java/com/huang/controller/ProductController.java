package com.huang.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.huang.domain.Brand;
import com.huang.domain.ProductInfo;
import com.huang.domain.ProductStyle;
import com.huang.domain.ProductType;
import com.huang.service.BrandService;
import com.huang.service.ProductService;
import com.huang.service.ProductTypeService;
import com.huang.util.ImageZipUtil;
import com.huang.util.PageUtil;
import com.huang.util.Pagenation;
import com.huang.vo.ProductVO;

@Controller
@RequestMapping("/controller/product")
public class ProductController {
	
	public static Logger log = Logger.getLogger(ProductController.class);
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private BrandService brandService;
	
	@RequestMapping("/list.do")
	public String toList(Model model,String currentPage,ProductVO pro){
		
		log.debug(currentPage);
		
		int pageSize = 3;
		if(currentPage==null||currentPage.equals(""))
			currentPage= "1";
		int cPage = Integer.parseInt(currentPage);
		
		int totalCount = 0;
		Pagenation page = null;
		List<ProductInfo> result = null;
		
		if("true".equals(pro.getQuery())){
			HashMap<String,Object> map = new HashMap<String,Object>();
			if(pro.getName()!=null&&!pro.getName().equals("")){
				map.put("name", "%"+pro.getName()+"%");
			}
			if(pro.getTypeid()!=null&&pro.getTypeid()>0){
				map.put("typeid", pro.getTypeid().intValue());
			}
			if(pro.getStartbaseprice()!=null&&pro.getStartbaseprice()>0){
				map.put("startbaseprice", pro.getStartbaseprice().floatValue());
			}
			if(pro.getEndbaseprice()!=null&&pro.getEndbaseprice()>0&&pro.getStartbaseprice()<pro.getEndbaseprice()){
				map.put("endbaseprice", pro.getEndbaseprice().floatValue());
			}
			if(pro.getStartsellprice()!=null&&pro.getStartsellprice()>0){
				map.put("startsellprice", pro.getStartsellprice().floatValue());
			}
			if(pro.getEndsellprice()!=null&&pro.getEndsellprice()>0&&pro.getEndsellprice()>pro.getStartsellprice()){
				map.put("endsellprice", pro.getEndsellprice().floatValue());
			}
			if(pro.getCode()!=null&&!pro.getCode().equals("")){
				map.put("code", pro.getCode());
			}
			if(pro.getBrandid()!=null&&!pro.getBrandid().equals("")&&!pro.getBrandid().equals("NONE")){
				map.put("brandid", pro.getBrandid());
			}
			
			totalCount = productService.countByDynamic(map);
			page = PageUtil.createPage(pageSize, cPage, totalCount);
			map.put("pageSize", page.getPageSize());
			map.put("pageOffset", page.getBeginIndex());
			result = productService.findByDynamic(map);
		}else{
			totalCount = productService.countAll();
			page = PageUtil.createPage(pageSize, cPage, totalCount);
			result = productService.find(page.getPageSize(), page.getBeginIndex());
		}
		if(pro!=null){
			model.addAttribute("pro", pro);
		}
		model.addAttribute("result", result);
		model.addAttribute("page", page);
		return "page/product/productlist";
	}
	
	@RequestMapping("/queryUI.do")
	public String toQuery(Model model){
		
		int count = brandService.countByName(null);
		List<Brand> list = brandService.findByName(null, count, 0);
		model.addAttribute("result", list);
		return "page/product/query_product";
	}
	
	@RequestMapping("/selectUI.do")
	public String toSelectUI(Model model,String typeid){
		int count = productTypeService.countwithparentid(typeid);
		List<ProductType> ret = productTypeService.findwithChild(count,0 , typeid);
		if(typeid!=null&&!typeid.equals("")){
			List<ProductType> parents = productTypeService.getParentTypes(Integer.parseInt(typeid));
			model.addAttribute("menutypes", parents);
		}
		model.addAttribute("result", ret);
		return "page/product/productTypeSelect";
	}
	
	@RequestMapping("/addUI.do")
	public String toAddUI(Model model){
		
		int count = brandService.countByName(null);
		List<Brand> list = brandService.findByName(null, count, 0);
		model.addAttribute("result", list);
		return "page/product/add_product";
	}
	
	@RequestMapping("/add.do")
	public String toAdd(Model model,ProductVO pvo,HttpServletRequest request) throws Exception{
		ProductInfo product = new ProductInfo(pvo);
		
		MultipartHttpServletRequest rm = (MultipartHttpServletRequest) request;

		CommonsMultipartFile cfile = (CommonsMultipartFile) rm
				.getFile("imagefile");

		if (cfile.getSize() > 0) {
			String suffix = cfile.getOriginalFilename().substring(
					cfile.getOriginalFilename().lastIndexOf(".")+1);
			String filename = UUID.randomUUID().toString() +"." +suffix;
			product.addProductStyle(new ProductStyle(pvo.getStylename(), filename));
			int productid = productService.add(product);
			
			saveImageFile(cfile.getBytes(),request,product.getType().getTypeid(),productid,filename);
			
			model.addAttribute("message", "产品添加成功");
			model.addAttribute("callback", "http://localhost:8080/shop/controller/product/list.do");
			return "page/share/message";
		}
		return "page/product/add_product";
	}
	
	@RequestMapping("/editUI.do")
	public String toEditUI(Model model,int productid) throws Exception{
		ProductInfo product = productService.getById(productid);
		model.addAttribute("product", product);
		int count = brandService.countByName(null);
		List<Brand> list = brandService.findByName(null, count, 0);
		model.addAttribute("result", list);
		return "page/product/edit_product";
	}
	
	@RequestMapping("/edit.do")
	public String toEdit(Model model,ProductVO pvo) throws Exception{
		ProductInfo product = productService.getById(pvo.getProductid());
		product.setProductVO(pvo);
		productService.update(product);
		model.addAttribute("message", "产品修改成功");
		model.addAttribute("callback", "http://localhost:8080/shop/controller/product/list.do");
		return "page/share/message";
	}
	
	@RequestMapping("/visible.do")
	public String toSetVisible(Model model,int[] productids){
		productService.setVisibleStatus(productids, true);
		model.addAttribute("message", "设置成功");
		model.addAttribute("callback", "http://localhost:8080/shop/controller/product/list.do");
		return "page/share/message";
	}
	
	@RequestMapping("/disvisible.do")
	public String toSetDisVisible(Model model,int[] productids){
		productService.setVisibleStatus(productids, false);
		model.addAttribute("message", "设置成功");
		model.addAttribute("callback", "http://localhost:8080/shop/controller/product/list.do");
		return "page/share/message";
	}
	
	@RequestMapping("/commend.do")
	public String toSetCommend(Model model,int[] productids){
		productService.setCommendStatus(productids, true);
		model.addAttribute("message", "设置成功");
		model.addAttribute("callback", "http://localhost:8080/shop/controller/product/list.do");
		return "page/share/message";
	}
	
	@RequestMapping("/discommend.do")
	public String toSetDisCommend(Model model,int[] productids){
		productService.setCommendStatus(productids, false);
		model.addAttribute("message", "设置成功");
		model.addAttribute("callback", "http://localhost:8080/shop/controller/product/list.do");
		return "page/share/message";
	}
	
	private static void saveImageFile(byte[] bfile,HttpServletRequest request,int typeid,int productid,String filename) throws Exception{
		
		String pathdir = "/images/product/" +typeid+"/"+productid+"/prototype";
		String prorealpathdir = request.getSession().getServletContext()
				.getRealPath(pathdir);
		File savedir = new File(prorealpathdir);
		if (!savedir.exists())
			savedir.mkdirs();
		File oldFile = new File(prorealpathdir,filename);
		OutputStream out = new FileOutputStream(oldFile);
		out.write(bfile);
		out.flush();
		out.close();
		
		String path140dir = "/images/product/" +typeid+"/"+productid+"/140";
		String real140pathdir = request.getSession().getServletContext()
				.getRealPath(path140dir);
		File save140dir = new File(real140pathdir);
		if (!save140dir.exists())
			save140dir.mkdirs();
		File newFile = new File(save140dir,filename);
		
		ImageZipUtil.zipImageFileWith140Width(oldFile,newFile);
	}
}
