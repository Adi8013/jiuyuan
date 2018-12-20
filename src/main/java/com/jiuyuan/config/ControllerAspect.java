package com.jiuyuan.config;

//@Component
//@Aspect
public class ControllerAspect {/*
	public static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

	@Pointcut("execution(* com.jiuyuan.*.*.controller.*.*(..))")
    public void executeService() {

    }
	
	@Around("executeService()")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) {

		Object result;	
		try {
			logger.debug("执行Controller开始: " + pjp.getSignature() + " 参数：" + Arrays.toString(pjp.getArgs()));
			//resultEntity = (ResultEntity<?>) pjp.proceed();
			//logger.debug("执行Controller结束: " + pjp.getSignature() + "， 返回值：" + pjp.get);
			result = pjp.proceed();	
		} catch (Throwable e) {
			//resultEntity = handlerException(pjp, throwable);
			logger.debug("异常{方法：" + pjp.getSignature() + "， 参数：" +Arrays.toString(pjp.getArgs()) + ",异常：" + e.getMessage() + "}", e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return result;
	}

	private ResultEntity<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
		ResultEntity<?> resultEntity = null;
		if (e instanceof RuntimeException) {
			logger.debug("RuntimeException{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage()
					+ "}", e);
			resultEntity = ResultEntity.fail(e.getMessage());
		} else {
			logger.debug("异常{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);
			resultEntity = ResultEntity.fail(e.getMessage());
		}

		return resultEntity;
	}

*/}
