package com.jiuyuan.config;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.jiuyuan.utils.OperatorUtil;

@Aspect //描述一个切面类，定义切面类的时候需要打上这个注解
@Component
public class AopConfiguration {
	@Pointcut("execution(* com.jiuyuan.*.*.service.impl.*.*_tran(..))")
    public void executeService() {

    }
    @Around("executeService()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) {
		Object result;	
		try {
			// 获得当前操作人信息
			String[]  operatorInfo = OperatorUtil.getOperatorInfo();
			// 获取切入点（即方法）的参数
			Object[] MethodArgsObject = pjp.getArgs();
			String strMethodName_Upper = null; 
			// 如有setUpdatePerson和setLastestUpdate方法使修改该参数
			for (Object aop_Object : MethodArgsObject) {
				//System.out.println("aop_Object:" + aop_Object);
				Method[] MethodNames =  aop_Object.getClass().getMethods();
				Object obj = null;
				for (Method methodName : MethodNames) {
					strMethodName_Upper = methodName.getName().toUpperCase();
					//System.out.println("strMethodName_Upper:" + strMethodName_Upper);
					if (strMethodName_Upper.equals("GETINSERTTIME")) {
						obj = methodName.invoke(aop_Object, null);
						break;
					}
				}
				for (Method methodName : MethodNames) {
					strMethodName_Upper = methodName.getName().toUpperCase();
					if (strMethodName_Upper.equals("SETUPDATEPERSON")) {
						methodName.invoke(aop_Object, operatorInfo[1]);
					} else if (strMethodName_Upper.equals("SETLASTESTUPDATE")) {
						methodName.invoke(aop_Object, operatorInfo[0]);
					} else if (strMethodName_Upper.equals("SETINSERTTIME")) {
						if(obj == null||obj.equals("")){
							methodName.invoke(aop_Object, operatorInfo[0]);
						}
					}
				}
			}
			result = pjp.proceed();	
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
}
