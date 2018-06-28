package com.anyb.ssm.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClientUtilTest {
	
	@Test
	public void decryptByPrivateKey(){
		String httpUrl = "http://127.0.0.1:8080/Springmvc_annotation/test/27";
		/*Map<String, String> maps = new HashMap<>();
		maps.put("name", "anyb");
		maps.put("age", "27");*/
		JSONObject maps = new JSONObject();
		maps.put("username", "anyb");
		maps.put("address", "cq");
		String str = HttpClientUtil.getInstance().sendHttpJsonPost(httpUrl, maps.toJSONString());
		//String str = HttpClientUtil.getInstance().sendHttpPost(httpUrl, "aaaaaabbbbb");
		System.out.println(str);
	}
	@Test
	public void testConvert() {
		String httpUrl = "http://127.0.0.1:8080/Springmvc_annotation/convert";
		StringEntity stringEntity = new StringEntity("1-anyb", "UTF-8");
		stringEntity.setContentType("application/x-wisely;charset=UTF-8");
		String str = HttpClientUtil.getInstance().sendHttpPost1(httpUrl,stringEntity);
		System.out.println(str);
	}
	@Test
	public void testConvert2() {
		String httpUrl = "http://127.0.0.1:8080/Springmvc_annotation/testServletInputStream";
		StringEntity stringEntity = new StringEntity("1-anyb", "UTF-8");
		String str = HttpClientUtil.getInstance().sendHttpPost1(httpUrl,stringEntity);
		System.out.println(str);
	}
	@Test
	public void testAdvice() {
		String httpUrl = "http://127.0.0.1:8080/Springmvc_annotation/advice";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "1");
		jsonObject.put("name", "anyb");
		String str = HttpClientUtil.getInstance().sendHttpJsonPost(httpUrl, jsonObject.toJSONString());
		System.out.println(str);
	}
}
