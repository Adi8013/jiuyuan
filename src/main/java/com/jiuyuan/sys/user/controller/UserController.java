package com.jiuyuan.sys.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiuyuan.sys.user.domain.User;
import com.jiuyuan.sys.user.service.UserService;
import com.alibaba.fastjson.JSON;  
import com.alibaba.fastjson.JSONArray;  

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/userlist")
	public String getUserList() {
		List<User> userList = userService.findAll();
		String reJSON = JSON.toJSONString(userList);
		System.out.println(reJSON);
		return reJSON;
	}
}
