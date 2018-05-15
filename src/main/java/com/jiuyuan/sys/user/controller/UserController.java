package com.jiuyuan.sys.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jiuyuan.sys.user.domain.User;
import com.jiuyuan.sys.user.service.UserService;
import com.jiuyuan.utils.SystemConstant;
import com.alibaba.fastjson.JSON;  

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/userlist")
	public String getUserList() {
		List<User> userList = userService.findAll();
		String reJSON = JSON.toJSONString(userList);
		return reJSON;
	}
	
	@RequestMapping(value="/addone", method = RequestMethod.POST)
	public String addUser(User user) {
		int result = userService.insert(user);
		if (result > 0) {
			return SystemConstant.SUCCESS;
		} else {
			return SystemConstant.ERROR;
		}
	}
}
