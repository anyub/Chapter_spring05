package com.anyb.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anyb.ssm.po.DemoObj;

@Controller
public class AdviceContorller {
	@RequestMapping("/advice")
	public String getSomething(@ModelAttribute("msg") String msg,@RequestBody DemoObj obj){
		throw new RuntimeException("测试错误");
	}
}
