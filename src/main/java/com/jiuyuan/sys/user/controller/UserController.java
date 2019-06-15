package com.jiuyuan.sys.user.controller;

import com.alibaba.fastjson.JSON;
import com.jiuyuan.sys.common.ResponseMsg;
import com.jiuyuan.sys.user.domain.User;
import com.jiuyuan.sys.user.service.UserService;
import com.jiuyuan.utils.OperatorUtil;
import com.jiuyuan.utils.SystemConstant;
import com.jiuyuan.utils.security.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
	
	@GetMapping("/findone")
	public String getUserByPK(String pk) {
		User user = userService.selectByPrimaryKey(pk);
		String reJSON = JSON.toJSONString(user);
		return reJSON;
	}

	@PostMapping("/search")
	public String findUserByUsername(String username) {
		List<User> userList = userService.findUserByUNlike(username);
		String reJSON = JSON.toJSONString(userList);
		return reJSON;
	}
	
	@PostMapping("/modifyone")
	public String updateUser(User user) {
		User user_db = userService.selectByPrimaryKey(user.getPk());
		// 设置前端传入的user值
		// 姓名
		user_db.setUserName(user.getUserName());
		// 密码
		if (user_db.getPassword().equals(user.getPassword().trim())) {
			user_db.setPassword(user.getPassword());
		} else {
			user_db.setPassword(EncryptUtil.encrypt(EncryptUtil.getSalt(user_db.getUserAccount().trim(), user.getPassword().trim())));
		}
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
	public ResponseMsg addUser(@Valid User user, BindingResult re) {
		ResponseMsg rm = new ResponseMsg();
		if (re.hasErrors()) {
			 rm.setMsg(re.getFieldError().getDefaultMessage());
			 return rm;
		}
		// 密码加密(加盐+｛｝)
		user.setPassword(EncryptUtil.encrypt(EncryptUtil.getSalt(user.getUserAccount().trim(), user.getPassword().trim())));
		//  注册日期
		user.setRegisterDate(OperatorUtil.getOperatorInfo()[0].substring(0, 10));
		int result = userService.insert_tran(user);
		if (result > 0) {
			 rm.setMsg(SystemConstant.SUCCESS);
			 return rm;
		} else {
			 rm.setMsg(SystemConstant.ERROR_TIP);
			 return rm;
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
