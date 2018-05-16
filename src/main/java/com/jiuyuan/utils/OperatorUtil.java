package com.jiuyuan.utils;

import static org.hamcrest.CoreMatchers.instanceOf;

import javax.servlet.http.HttpSession;

import com.jiuyuan.sys.context.SessionContextHelper;
import com.jiuyuan.sys.user.domain.User;

public class OperatorUtil {
	/**
	 * 取得当前更新时间和操作用户信息
	 * 
	 * @return 数组 0:当前时间，1：当前用户帐号 2：当前用户名称
	 */ 
	public static String[] getOperatorInfo() {
		String[] returnArr = new String[3];
		returnArr[0] = DateUtil.getSystemDate();
		// 取得当前session
		HttpSession session = SessionContextHelper.getSession();
		String userAccount = null;
		String userName = null;
		if (session != null) {
			Object obj = session.getAttribute(SystemConstant.SYS_USER);
			if (obj instanceof User) {
				userAccount = ((User) obj).getUserAccount();
				userName = ((User) obj).getUserName();
			}
		}
		returnArr[1] = userAccount==null?"":userAccount;
		returnArr[2] = userName==null?"":userName;
		return returnArr;
	}
}
