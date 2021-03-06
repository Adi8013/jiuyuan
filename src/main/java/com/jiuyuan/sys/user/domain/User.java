package com.jiuyuan.sys.user.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class User implements Serializable{
	private String pk;
	@NotBlank(message = "帐号不能为空")
	@Pattern(regexp = "[0-9a-zA-Z]*", message = "帐号必须由字母和数字组成")
	private String userAccount;
	@NotBlank(message = "用户名不能为空")
	private String userName;

	private String userDeptCode;

	private String userOrgCode;
	@NotBlank(message = "密码不能为空")
	@Pattern(regexp = "[0-9a-zA-Z]{6,20}", message = "密码必须是数字或字母，长度6-20位")
	private String password;
	@Pattern(regexp = "^((13[0-9])|(14[5|7])|(16[0-9])|(17[0-9])|(15([0-3]|[5-9]))|(18[0-9]))\\d{8}$", message = "请正确输入11位手机号码")
	private String phone;

	private String email;

	private String registerDate;

	private String lastLoginDate;

	private String addMenu;

	private String usualMenu;

	private String userLevelCode;

	private String minusMenu;

	private String headPath;
	
	private String remark;

	private String insertTime;

	private String lastestUpdate;

	private String updatePerson;

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDeptCode() {
		return userDeptCode;
	}

	public void setUserDeptCode(String userDeptCode) {
		this.userDeptCode = userDeptCode;
	}

	public String getUserOrgCode() {
		return userOrgCode;
	}

	public void setUserOrgCode(String userOrgCode) {
		this.userOrgCode = userOrgCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getAddMenu() {
		return addMenu;
	}

	public void setAddMenu(String addMenu) {
		this.addMenu = addMenu;
	}

	public String getUsualMenu() {
		return usualMenu;
	}

	public void setUsualMenu(String usualMenu) {
		this.usualMenu = usualMenu;
	}

	public String getUserLevelCode() {
		return userLevelCode;
	}

	public void setUserLevelCode(String userlLevelCode) {
		this.userLevelCode = userlLevelCode;
	}

	public String getMinusMenu() {
		return minusMenu;
	}

	public void setMinusMenu(String minusMenu) {
		this.minusMenu = minusMenu;
	}

	public String getHeadPath() {
		return headPath;
	}

	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getLastestUpdate() {
		return lastestUpdate;
	}

	public void setLastestUpdate(String lastestUpdate) {
		this.lastestUpdate = lastestUpdate;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}
}