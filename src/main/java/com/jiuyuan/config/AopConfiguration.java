package com.jiuyuan.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AopConfiguration {
	@Pointcut("execution(* com.jiuyuan.*.*.service.*(..))")
    public void executeService() {

    }
}
