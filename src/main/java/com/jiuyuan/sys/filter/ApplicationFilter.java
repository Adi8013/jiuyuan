package com.jiuyuan.sys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jiuyuan.sys.context.SessionContextHelper;
import com.jiuyuan.utils.SystemConstant;

public class ApplicationFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		// 为http会话支持类填充request和response，方便调用
		SessionContextHelper.setRequest(httpRequest);
		SessionContextHelper.setResponse(httpResponse);
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//表示该页面可以在相同域名页面的 frame 中展示。不允许被其他网页中嵌套
		httpResponse.addHeader("x-frame-options", "SAMEORIGIN");
		//System.out.println("*****************进入过滤器");
		String path = httpRequest.getServletPath();
		//System.out.println("路径：" + path);
		if(!path.endsWith(".js") && !path.endsWith(".css")&& !path.endsWith(".ico")&& !path.endsWith(".png")&& !path.endsWith(".jpg")
				&& !path.equals("/login")&& !path.equals("/defaultCaptcha")&& !path.equals("/doCheckLogin")){
			System.out.println("拦截路径***"+path );
			HttpSession session = httpRequest.getSession();
			if(session.getAttribute(SystemConstant.SYS_USER) == null){
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
				return;
			}
		} 
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
