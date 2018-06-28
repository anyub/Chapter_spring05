package com.anyb.ssm.converter;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.anyb.ssm.po.DemoObj;

//继承AbstractHttpMessageConverter接口来实现自定义的HttpMessageConverter
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {
	
	public MyMessageConverter() {
		//新建一个我们自定义的媒体类型application/x-wisely
		super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
	}
	
	@Override
	/**
	 * 表明本HttpMessageConverter只处理DemoObj这个类
	 */
	protected boolean supports(Class<?> clazz) {
		return DemoObj.class.isAssignableFrom(clazz);
	}

	@Override
	//处理请求数据,我们处理由“-”隔开的数据，并转化为DemoObj对象
	protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
		String[] tempArr = temp.split("-");
		DemoObj demoObj = new DemoObj(Integer.parseInt(tempArr[0]), tempArr[1]);
		return demoObj;
	}

	@Override
	/**
	 * 重写writeInternal，处理如何输出数据到response
	 */
	protected void writeInternal(DemoObj t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		String out = "hello:"+t.getId()+"-"+t.getName();
		outputMessage.getBody().write(out.getBytes("UTF-8"));
	}

}
