package com.louis.mango.admin.config;

import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.louis.mango.admin.model.ProcessingResult;
//捕捉参数错误
@ControllerAdvice
public class BingExceptionHandler {
	@ExceptionHandler(BindException.class)
    @ResponseBody
    public ProcessingResult handleBindException(Exception e) {
		ProcessingResult pr = new ProcessingResult();
        //打印校验住的所有的错误信息
//        StringBuilder sb = new StringBuilder("参数错误：[");
        List<ObjectError> list = ((BindException) e).getAllErrors();
//        list.get(0).getDefaultMessage();
//        for (ObjectError item : list) {
//            sb.append(item.getDefaultMessage()).append(',');
//        }
//        sb.deleteCharAt(sb.length() - 1);
//        sb.append(']');
//
//        String msg = sb.toString();
        pr.setResultContent(list.get(0).getDefaultMessage());
        return pr;
    }
}
