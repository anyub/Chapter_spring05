package com.anyb.ssm.controller;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anyb.ssm.po.DemoObj;

@Controller
public class ConverterController {
	@RequestMapping(value = "/convert",produces= {"application/x-wisely;charset=UTF-8"})
	@ResponseBody
	public DemoObj convert(@RequestBody DemoObj demoObj,HttpServletRequest request) {
		System.out.println(demoObj);
		return demoObj;
	}
	@RequestMapping("/testServletInputStream")
	public String testServletInputStream(HttpServletRequest request,ModelMap map) {
		//map.addAttribute("msge", "OK");
		try {
			ServletInputStream input = request.getInputStream();
			int length = request.getContentLength();
			byte[] b = new byte[length];
			input.read(b, 0, length);
			System.out.println(new String(b));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("msge", "OK");
		return "index";
	}
}
