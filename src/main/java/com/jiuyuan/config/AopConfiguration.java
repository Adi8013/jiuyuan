package com.jiuyuan.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.jiuyuan.utils.OperatorUtil;

@Aspect //描述一个切面类，定义切面类的时候需要打上这个注解
@Component
public class AopConfiguration {
	@Pointcut("execution(* com.jiuyuan.*.*.service.*.insert*(..))")
    public void executeService() {

    }
    @Before("executeService()")
    public void qianzhi(){
        System.out.println("前置通知");
     // 获得当前操作人信息
     String[]  operatorInfo = OperatorUtil.getOperatorInfo();
     System.out.println(operatorInfo.toString());
    }
    @Around("executeService()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) {
		Object result;	
		try {
			// 获得当前操作人信息
			String[]  operatorInfo = OperatorUtil.getOperatorInfo();
			// 获取切入点（即方法）的参数
			Object[] MethodArgsObject = pjp.getArgs();
			System.out.println(Arrays.toString(operatorInfo));
			System.out.println("获取切入点（即方法）的参数" + MethodArgsObject);
			String strMethodName_Upper = null; 
			// 如有setUpdatePerson和setLastestUpdate方法使修改该参数
			for (Object aop_Object : MethodArgsObject) {
				Method[] MethodNames =  aop_Object.getClass().getMethods();
				Object obj = null;
				for (Method methodName : MethodNames) {
					strMethodName_Upper = methodName.getName().toUpperCase();
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
					} else if (strMethodName_Upper.equals("GETINSERTTIME")) {
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
