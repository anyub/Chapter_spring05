package com.anyb.ssm.exceptionHandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

//声明一个控制器建言，@ControllerAdvice组合了@Component注解，所以自动注册为spring的Bean
@ControllerAdvice
public class ExceptionHandlerAdvice {
	//在此处定义全局处理，通过@ExceptionHandler的value属性可过滤拦截条件，
	//此处拦截所有Exception
	@ExceptionHandler
	public ModelAndView exception(Exception exception,WebRequest request){
		ModelAndView view = new ModelAndView("error");
		view.addObject("errorMsg", exception.getMessage());
		return view;
	}
	//通过@ModelAttribute注解将键值对添加到全局，所有注解的@RequestMapping的方法可以获得该键值对
	@ModelAttribute
	public void addAttributes(Model model){
		model.addAttribute("msg", "额外信息");
	}
	//定制WebDataBinder
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.setDisallowedFields("id");//忽略request参数的id
	}
}
