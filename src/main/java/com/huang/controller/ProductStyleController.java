package com.huang.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.huang.domain.ProductInfo;
import com.huang.domain.ProductStyle;
import com.huang.service.ProductService;
import com.huang.service.ProductStyleService;
import com.huang.vo.ProductVO;


@Controller
@RequestMapping("/productstyle")
public class ProductStyleController {

	@Resource
	private ProductStyleService productStyleService;
	
	@Resource
	private ProductService productService;
	
	@RequestMapping("/list.do")
	public String toList(Model model,String productid){
		
		ProductInfo product = productService.getById(Integer.parseInt(productid));
		List<ProductStyle> ret = productStyleService.find(Integer.parseInt(productid));
		for(ProductStyle p : ret){
			p.setProduct(product);
		}
		model.addAttribute("result", ret);
		model.addAttribute("productid", productid);
		return "page/product/productstylelist";
	}
	
	@RequestMapping("/addUI.do")
	public String toAddUI(Model model,String productid){
		
		model.addAttribute("productid", productid);
		return "page/product/add_productstyle";
	}
	
	@RequestMapping("/editUI.do")
	public String toEditUI(Model model,String productid,String id){
		ProductInfo pro = productService.getById(Integer.parseInt(productid));
		ProductStyle ps = productStyleService.getById(Integer.parseInt(id));
		ps.setProduct(pro);
		model.addAttribute("result", ps);
		return "page/product/edit_productstyle";
	}
	
	@RequestMapping("/add.do")
	public String toAdd(Model model,ProductVO pvo,HttpServletRequest request)
			throws Exception {
		
		MultipartHttpServletRequest rm = (MultipartHttpServletRequest) request;

		CommonsMultipartFile cfile = (CommonsMultipartFile) rm
				.getFile("imagefile");

		if (cfile.getSize() > 0) {
			String suffix = cfile.getOriginalFilename().substring(
					cfile.getOriginalFilename().lastIndexOf(".")+1);
			String filename = UUID.randomUUID().toString() +"." +suffix;
			ProductInfo product = productService.getById(pvo.getProductid().intValue());
			ProductStyle ps = new ProductStyle(pvo.getName(), filename);
			ps.setProduct(product);
			productStyleService.add(ps);
			byte[] bfile = cfile.getBytes();
			String pathdir = "/images/product/" +product.getType().getTypeid()+"/"+pvo.getProductid()+"/prototype";
			String realpathdir = request.getSession().getServletContext()
					.getRealPath(pathdir);
			File savedir = new File(realpathdir);
			if (!savedir.exists())
				savedir.mkdirs();
			OutputStream out = new FileOutputStream(new File(realpathdir,
					filename));
			out.write(bfile);
			out.flush();
			out.close();
			model.addAttribute("message", "产品样式添加成功");
			model.addAttribute("callback", "http://localhost:8080/shop/productstyle/list.do?productid="+product.getId());
			return "page/share/message";
		}
		return "page/product/add_productstyle";
	}
	
	@RequestMapping("/edit.do")
	public String toEdit(Model model,ProductVO pvo,HttpServletRequest request)
			throws Exception {
		
		MultipartHttpServletRequest rm = (MultipartHttpServletRequest) request;

		CommonsMultipartFile cfile = (CommonsMultipartFile) rm
				.getFile("imagefile");

		ProductInfo product = productService.getById(pvo.getProductid().intValue());
		ProductStyle ps = productStyleService.getById(pvo.getId());
		if(pvo.getName()!=null&&!pvo.getName().equals("")){
			ps.setName(pvo.getName());
		}
		if (cfile.getSize() > 0) {
			String suffix = cfile.getOriginalFilename().substring(
					cfile.getOriginalFilename().lastIndexOf(".")+1);
			String filename = UUID.randomUUID().toString() +"." +suffix;
			ps.setImagename(filename);
			ps.setProduct(product);
			byte[] bfile = cfile.getBytes();
			String pathdir = "/images/product/" +product.getType().getTypeid()+"/"+pvo.getProductid()+"/prototype";
			String realpathdir = request.getSession().getServletContext()
					.getRealPath(pathdir);
			File savedir = new File(realpathdir);
			if (!savedir.exists())
				savedir.mkdirs();
			OutputStream out = new FileOutputStream(new File(realpathdir,
					filename));
			out.write(bfile);
			out.flush();
			out.close();
		}
		productStyleService.update(ps);
		model.addAttribute("message", "产品样式修改成功");
		model.addAttribute("callback", "http://localhost:8080/shop/productstyle/list.do?productid="+product.getId());
		return "page/share/message";
	}
}
