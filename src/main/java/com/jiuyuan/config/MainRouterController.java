package com.jiuyuan.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainRouterController {
	/**
	 * 用户管理列表
	 * @return
	 */
	@RequestMapping("userjsp")
	public String user() {
		return "sys/user";
	}
	/**
	 * 销售单管理列表
	 * @return
	 */
	@RequestMapping("salejsp")
	public String sale() {
		return "module/salemanager/salemanager";
	}
}
