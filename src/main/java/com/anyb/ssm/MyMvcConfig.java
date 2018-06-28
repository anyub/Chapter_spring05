package com.anyb.ssm;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.anyb.ssm.controller.ItemsController;
import com.anyb.ssm.converter.MyMessageConverter;
import com.anyb.ssm.exceptionHandler.ExceptionHandlerAdvice;
import com.anyb.ssm.interceptors.DemoInterceptor;

@Configuration
//开启springMVC的支持，不然重写WebMvcConfigurerAdapter的方法无效
@EnableWebMvc
@ComponentScan(basePackageClasses={ItemsController.class,ExceptionHandlerAdvice.class})
public class MyMvcConfig extends WebMvcConfigurerAdapter {
	@Bean
	//配置视图解析器
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
	@Bean
	//将控制器交给Spring容器管理
	public DemoInterceptor demoInterceptor(){
		return new DemoInterceptor();
	}
	@Bean(name="multipartResolver")
	//上传设置
	public MultipartResolver getMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(1024*1024*1);
		return multipartResolver;
	}
	@Override
	//配置静态资源
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")//指定对外暴露的访问路径
			.addResourceLocations("classpath:/static/");//指定文件放置的目录
	}
	@Override
	//添加拦截器
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor())
			.addPathPatterns("/**")//设置拦截路径
			.excludePathPatterns("/queryItems/**");//设置不拦截路径
	}
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		//设置不忽略.符号
		configurer.setUseSuffixPatternMatch(false);
	}
	/**
	 * 无任何业务知识简单的页面跳转，可用使用下面方法注册
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/toUpload").setViewName("/upload");
	}
	
	/**
	 * 配置自定义的HttpMessageConverter到SpringMvc中2个方法
	 * 	configureMessageConverters：该方法会覆盖springMvc中默认的多个HttpMessageConverter
	 * 	extendMessageConverters：仅添加自定义的HttpMessageConverter，不覆盖默认注册的HttpMessageConverter
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(getMyMessageConverter());
	}
	//添加自定义的HttpMessageConverter
	@Bean(name="myMessageConverter")
	public MyMessageConverter getMyMessageConverter() {
		return new MyMessageConverter();
	}
}
