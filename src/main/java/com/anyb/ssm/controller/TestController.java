package com.anyb.ssm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anyb.ssm.po.User;

@Controller
public class TestController {
	@RequestMapping(value = "/test/{age}",produces= {"application/xml;charset=UTF-8"})
	@ResponseBody
	public User test(@RequestBody User user,@PathVariable String age,HttpServletRequest request){
		System.out.println(user);
		return user;
	}
	
	@RequestMapping(value = "/test2")
	@ResponseBody
	public String test2(String name,String age) {
		System.out.println(name);
		System.out.println(age);
		return "aaa";
	}
}
