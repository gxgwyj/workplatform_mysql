package com.junyang.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="media/")
public class MediaAction {
	
	@RequestMapping(value = "/image/getImage")
	public void getImage(HttpServletRequest request,HttpServletResponse response,@RequestParam("path") String path) {
	    FileInputStream fis = null;
	    response.setContentType("image/"+path.substring(path.lastIndexOf(".")+1));
	    try {
	        OutputStream out = response.getOutputStream();
	        File file = new File(path);
	        fis = new FileInputStream(file);
	        byte[] b = new byte[fis.available()];
	        fis.read(b);
	        out.write(b);
	        out.flush();
	    } catch (Exception e) {
	         e.printStackTrace();
	    } finally {
	        if (fis != null) {
	            try {
	               fis.close();
	            } catch (IOException e) {
	            e.printStackTrace();
	        }   
	          }
	    }
}
}
