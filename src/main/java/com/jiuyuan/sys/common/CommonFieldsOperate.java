package com.jiuyuan.sys.common;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;

import com.jiuyuan.utils.OperatorUtil;

/**
 * 通用设置
 * 每条数据的新增、修改操作时同时设置"最后更新时间"和"更新人"，“插入时间”字段依据原来是否有值进行赋值
 */

public class CommonFieldsOperate {
	/**
	 * 修改切入点参数，即修改对象的UpdatePerson和LastestUpdate和getInsertTime属性
	 * @param pjp 切入点 即方法
	 * @return
	 */
	public Object doBasicProfiling(ProceedingJoinPoint pjp) {
		Object result = "";	
		try {
			// 获得当前操作人信息
			String[]  operatorInfo = OperatorUtil.getOperatorInfo();
			// 获取切入点（即方法）的参数
			Object[] MethodArgsObject = pjp.getArgs();
			System.out.println("获取切入点（即方法）的参数" + MethodArgsObject);
			String strMethodName_Upper = null; 
			// 如有setUpdatePerson和setLastestUpdate方法使修改该参数
			for (Object aop_Object : MethodArgsObject) {
				System.out.println("aop_Object:" + aop_Object);
				/*
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
			*/
			}
			result = pjp.proceed();	
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
	
}
