package com.louis.mango.admin.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.louis.mango.admin.model.ProcessingResult;
//忘了是干啥的了，好像没有用
@ControllerAdvice
public class IMoocExceptionHandler {
	
	public static final String ResultContent = "error";
	
	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest request,
		HttpServletResponse response, Exception e)throws  Exception{
		e.printStackTrace();
		
		ModelAndView mavAndView = new ModelAndView();
		mavAndView.addObject("exception", e);
		mavAndView.addObject("url", request.getRequestURI());
		mavAndView.setViewName(ResultContent);
		return mavAndView;
	}
	
}
