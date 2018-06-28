package com.anyb.ssm.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	@RequestMapping("/upload")
	@ResponseBody
	public String uploadFile(MultipartFile file_1,HttpServletRequest request,HttpServletResponse response) {
		try {
			FileUtils.writeByteArrayToFile(new File("E:/upload/"+file_1.getOriginalFilename()), file_1.getBytes(), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
}
