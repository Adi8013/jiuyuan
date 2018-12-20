package com.jiuyuan.config;

import com.jiuyuan.sys.common.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	public ResponseMsg defaultErrorHandler(HttpServletRequest req, Exception e) {
		logger.debug("异常：" + e.getMessage() + "}", e);
		return ResponseMsg.error().push("error", e.getMessage());
	}

}
