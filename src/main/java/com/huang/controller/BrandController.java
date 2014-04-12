package com.huang.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.huang.domain.Brand;
import com.huang.service.BrandService;
import com.huang.util.PageUtil;
import com.huang.util.Pagenation;

@Controller
@RequestMapping("/controller/brand")
public class BrandController {

	@Resource
	private BrandService brandService;

	@RequestMapping("/list.do")
	public String toList(Model model, String currentPage, String name,
			String query) {
		int pageSize = 3;
		if (currentPage == null || currentPage.equals(""))
			currentPage = "1";
		int cPage = Integer.parseInt(currentPage);

		int totalCount = brandService.countByName(name);
		Pagenation page = PageUtil.createPage(pageSize, cPage, totalCount);

		List<Brand> result = brandService.findByName(name, page.getPageSize(),
				page.getBeginIndex());

		model.addAttribute("name", name);
		model.addAttribute("query", query);
		model.addAttribute("result", result);
		model.addAttribute("page", page);

		return "page/product/brandlist";
	}

	@RequestMapping("/addUI.do")
	public String toAddUI() {
		return "page/product/add_brand";
	}

	@RequestMapping("/add.do")
	public String toAdd(Model model, Brand brand, HttpServletRequest request)
			throws Exception {

		MultipartHttpServletRequest rm = (MultipartHttpServletRequest) request;

		CommonsMultipartFile cfile = (CommonsMultipartFile) rm
				.getFile("logofile");

		String uuid = UUID.randomUUID().toString();
		brand.setCode(uuid);
		if (!cfile.isEmpty() && cfile.getSize() > 0) {
			byte[] bfile = cfile.getBytes();
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/HH");
			String logopathdir = "/images/brand/" + format.format(new Date());
			String logorealpathdir = request.getSession().getServletContext()
					.getRealPath(logopathdir);
			File logosavedir = new File(logorealpathdir);
			if (!logosavedir.exists())
				logosavedir.mkdirs();
			String suffix = cfile.getOriginalFilename().substring(
					cfile.getOriginalFilename().lastIndexOf("."));
			String imagename = brand.getCode() + suffix;
			OutputStream out = new FileOutputStream(new File(logorealpathdir,
					imagename));
			out.write(bfile);
			out.flush();
			out.close();
			brand.setLogopath(logopathdir + "/" + imagename);
		}
		brandService.add(brand);
		model.addAttribute("message", "品牌添加成功");
		model.addAttribute("callback", "http://localhost:8080/shop/controller/brand/list.do");
		return "page/share/message";
	}

	@RequestMapping("/queryUI.do")
	public String toQueryUI(Model model, String typeid) {
		return "page/product/query_brand";
	}

	@RequestMapping("/editUI.do")
	public String toEditUI(Model model, String code) {
		Brand brand = brandService.getByCode(code);
		model.addAttribute("brand", brand);
		return "page/product/edit_brand";
	}

	@RequestMapping("/edit.do")
	public String toEdit(Model model, Brand brand, HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest rm = (MultipartHttpServletRequest) request;

		CommonsMultipartFile cfile = (CommonsMultipartFile) rm
				.getFile("logofile");
		if (!cfile.isEmpty() && cfile.getSize() > 0) {
			byte[] bfile = cfile.getBytes();
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/HH");
			String logopathdir = "/images/brand/" + format.format(new Date());
			String logorealpathdir = request.getSession().getServletContext()
					.getRealPath(logopathdir);
			File logosavedir = new File(logorealpathdir);
			if (!logosavedir.exists())
				logosavedir.mkdirs();
			String suffix = cfile.getOriginalFilename().substring(
					cfile.getOriginalFilename().lastIndexOf("."));
			String imagename = UUID.randomUUID().toString() + suffix;
			OutputStream out = new FileOutputStream(new File(logorealpathdir,
					imagename));
			out.write(bfile);
			out.flush();
			out.close();
			brand.setLogopath(logopathdir + "/" + imagename);
		}
		brandService.update(brand);
		model.addAttribute("message", "品牌修改成功");
		model.addAttribute("callback", "http://localhost:8080/shop/controller/brand/list.do");
		return "page/share/message";
	}

}
