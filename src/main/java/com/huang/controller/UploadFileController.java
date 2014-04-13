package com.huang.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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

import com.huang.domain.UploadFile;
import com.huang.service.UploadFileService;
import com.huang.util.FileTypeAllowUtil;
import com.huang.util.PageUtil;
import com.huang.util.Pagenation;

@Controller
@RequestMapping("/controller/upload")
public class UploadFileController {
	
	@Resource
	private UploadFileService uploadFileService;
	
	@RequestMapping("/list.do")
	public String toList(Model model, String currentPage) {
		int pageSize = 3;
		if (currentPage == null || currentPage.equals(""))
			currentPage = "1";
		int cPage = Integer.parseInt(currentPage);

		int totalCount = uploadFileService.count();
		Pagenation page = PageUtil.createPage(pageSize, cPage, totalCount);

		List<UploadFile> result = uploadFileService.find(page.getPageSize(),
				page.getBeginIndex());
		model.addAttribute("result", result);
		model.addAttribute("page", page);

		return "page/uploadfile/filelist";
	}
	
	@RequestMapping("/addUI.do")
	public String toAddUI() {
		return "page/uploadfile/upload";
	}

	@RequestMapping("/add.do")
	public String toAdd(Model model,HttpServletRequest request)
			throws Exception {

		MultipartHttpServletRequest rm = (MultipartHttpServletRequest) request;

		CommonsMultipartFile cfile = (CommonsMultipartFile) rm
				.getFile("uploadfile");

		if (!cfile.isEmpty() && cfile.getSize() > 0) {
			String suffix = cfile.getOriginalFilename().substring(
					cfile.getOriginalFilename().lastIndexOf(".")+1);
			String fileType = cfile.getContentType();
			
			if(!FileTypeAllowUtil.validateFileType(suffix, fileType)){
				model.addAttribute("message", "文件格式不正确");
				return "page/share/message";
			}
			
			byte[] bfile = cfile.getBytes();
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/HH");
			String pathdir = "/images/uploadfile/" + format.format(new Date());
			String realpathdir = request.getSession().getServletContext()
					.getRealPath(pathdir);
			File savedir = new File(realpathdir);
			if (!savedir.exists())
				savedir.mkdirs();
			
			String filename = UUID.randomUUID().toString() +"." +suffix;
			OutputStream out = new FileOutputStream(new File(realpathdir,
					filename));
			out.write(bfile);
			out.flush();
			out.close();
			
			UploadFile uf = new UploadFile(pathdir+"/"+filename);
			uploadFileService.add(uf);
			model.addAttribute("message", "文件上传成功");
		}else{
			model.addAttribute("message", "请上传文件");
		}
		
		model.addAttribute("callback", "/controller/upload/list.do");
		return "page/uploadfile/fileuploadfinish";
	}
	
	@RequestMapping("/delete.do")
	public String toBatchDelete(Model model,Integer[] fields,HttpServletRequest request)
			throws Exception {
		if(fields==null||fields.length<=0){
			return "redirect:list.do";
		}
		List<Integer> ids = Arrays.asList(fields);
		List<UploadFile> uploadFiles = uploadFileService.batchselect(ids);
		File savepath = null;
		for(int i = 0;i<uploadFiles.size();i++){
			String realpathdir = request.getSession().getServletContext().getRealPath(uploadFiles.get(i).getFilepath());
			savepath = new File(realpathdir);
			if(savepath.exists()){
				savepath.delete();
			}
		}
		uploadFileService.batchdelete(ids);
		return "redirect:list.do";

	}
}
