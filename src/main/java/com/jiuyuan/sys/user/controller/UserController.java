package com.jiuyuan.sys.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiuyuan.sys.user.domain.User;
import com.jiuyuan.sys.user.service.UserService;
import com.jiuyuan.utils.SystemConstant;
import com.jiuyuan.utils.security.EncryptUtil;
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
	
	@RequestMapping("/findone")
	public String getUserByPK(String pk) {
		User user = userService.selectByPrimaryKey(pk);
		String reJSON = JSON.toJSONString(user);
		return reJSON;
	}
	
	@RequestMapping(value="/addone", method = RequestMethod.POST)
	public String addUser(User user) {
		// 密码加密
		user.setPassword(EncryptUtil.encrypt(user.getPassword()));
		int result = userService.insert(user);
		if (result > 0) {
			return SystemConstant.SUCCESS;
		} else {
			return SystemConstant.ERROR;
		}
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteUsers(@RequestParam(value="pks[]")String[] pks) {
		int result = userService.deleteByPks(pks);
		if (result > 0) {
			return SystemConstant.SUCCESS;
		} else {
			return SystemConstant.ERROR;
		}
	}
	
	@RequestMapping(value="/checkUserAccount", method = RequestMethod.GET)
	public boolean checkcheckUserAccount(String userAccount) {
		if (userService.loginUser(userAccount) == null) {
			return true;
		} else {
			return false;
		}
	}
}
