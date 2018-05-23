package com.jiuyuan.sys.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiuyuan.sys.user.domain.User;
import com.jiuyuan.sys.user.service.UserService;
import com.jiuyuan.utils.OperatorUtil;
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
	
	@RequestMapping("/modifyone")
	public String updateUser(User user) {
		User user_db = userService.selectByPrimaryKey(user.getPk());
		// 设置前端传入的user值
		// 姓名
		user_db.setUserName(user.getUserName());
		// 密码
		user_db.setPassword(user.getPassword());
		// 电话
		user_db.setPhone(user.getPhone());
		// 邮箱
		user_db.setEmail(user.getEmail());
		
		int result = userService.updateByPrimaryKey_tran(user_db);
		if (result > 0) {
			return SystemConstant.SUCCESS;
		} else {
			return SystemConstant.ERROR;
		}
	}
	
	@RequestMapping(value="/addone", method = RequestMethod.POST)
	public String addUser(User user) {
		// 密码加密
		user.setPassword(EncryptUtil.encrypt(user.getPassword()));
		//  注册日期
		user.setRegisterDate(OperatorUtil.getOperatorInfo()[0].substring(0, 10));
		int result = userService.insert_tran(user);
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
