package com.example.demo.rest;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class RestBase {

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	/**
	 * 
	 * @Title: getOutputStream
	 * @param:
	 * @Description:
	 * @return long
	 * @throws IOException
	 */
	public OutputStream getOutputStream() throws IOException {
		// 设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的相应图片
		response.setContentType("image/jpeg");
		return response.getOutputStream();
	}

	/**
	 * 
	 * @Title: getOutputStream
	 * @param:
	 * @Description:
	 * @return long
	 * @throws IOException
	 */
	public HttpServletRequest getRequest() throws IOException {
		return request;
	}
}
