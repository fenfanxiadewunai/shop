package com.huang.domain;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.huang.util.ImageSizer;
import com.huang.util.ImageZipUtil;

public class testImageSizer {
	
	@Test
	public void createImage(){
		try {
			ImageSizer.resize(new File("/Users/fenfanxiadewunai/Pictures/20090123051324311829.jpg"), new File("/Users/fenfanxiadewunai/Pictures/2.jpg"), 320, "jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void zipJpeg(){
			String path = ImageZipUtil.zipImageFileWithWidth(new File("/Users/fenfanxiadewunai/Pictures/20090123051324311829.jpg"), new File("/Users/fenfanxiadewunai/Pictures/2.jpg"), 140);
			System.out.println(path);;
	}
	
	
}



