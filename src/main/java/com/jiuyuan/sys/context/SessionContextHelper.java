package com.jiuyuan.sys.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * http会话对象支持类
 * @author Adi
 */
public class SessionContextHelper {
	
	private static final ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<HttpServletRequest>(); 
	
	private static final ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<HttpServletResponse>(); 
	
	private static final ThreadLocal<String> requestDataAuthIdThreadLocal = new ThreadLocal<String>();
	/**
	 * 设置当前线程的http请求对象，通常不允许具体业务应用调用该函数。
	 * @param request
	 */
	public static void setRequest(HttpServletRequest request){
		requestThreadLocal.set(request);
	}
	
	/**
	 * 取得当前线程的http请求对象
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return requestThreadLocal.get();
	}
	
	/**
	 * 取得当前线程的http会话对象
	 * @return
	 */
	public static HttpSession getSession(){
		HttpServletRequest req = requestThreadLocal.get();
		return req==null?null:req.getSession();
	}
	
	/**
	 * 设置当前线程的http响应对象，通常不允许具体业务应用调用该函数。
	 * @param response
	 */
	public static void setResponse(HttpServletResponse response){
		responseThreadLocal.set(response);
	}
	
	/**
	 * 取得当前线程的http响应对象，通常没特殊需求应用时不应该调用该函数。
	 * @return
	 */
	public static HttpServletResponse getResponse(){
		return responseThreadLocal.get();
	}
	
	/**
	 * 取得当前线程的数据权限标识
	 * @return
	 */
	public static String getRequestDataAuthId(){
		return requestDataAuthIdThreadLocal.get();
	}
	
	/**
	 * 设置当前线程的数据权限标识
	 * @param requestDataAuthId 来自request请求的数据权限标识
	 */
	public static void setRequestDataAuthId(String requestDataAuthId){
		//空设置成null,表示清除原来的值或本身就没有值
		requestDataAuthId = (requestDataAuthId!=null && requestDataAuthId.length()==0)?null:requestDataAuthId;
		requestDataAuthIdThreadLocal.set(requestDataAuthId);
	}
}
