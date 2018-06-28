package com.anyb.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.anyb.ssm.po.Items;

/**
 * 使用注解开发
 * @author anyb
 *
 */
//使用@Controller标识处理器
@Controller
public class ItemsController {
	//使用@RequestMapping实现方法和Url的映射
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//调用service查找 数据库，查询商品列表，这里使用静态数据模拟
		List<Items> itemsList = new ArrayList<Items>();
		//向list中填充静态数据
		Items items_1 = new Items();
		items_1.setName("联想笔记本");
		items_1.setPrice(6000f);
		items_1.setDetail("ThinkPad T430 联想笔记本电脑！");
		
		Items items_2 = new Items();
		items_2.setName("苹果手机");
		items_2.setPrice(5000f);
		items_2.setDetail("iphone6苹果手机！");
		
		itemsList.add(items_1);
		itemsList.add(items_2);

		//返回ModelAndView
		ModelAndView modelAndView =  new ModelAndView();
		//相当 于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);
		//指定视图
		/*modelAndView.setViewName("/jsp/items/itemsList.jsp");*/
		//设置了前缀和后缀
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}

}
