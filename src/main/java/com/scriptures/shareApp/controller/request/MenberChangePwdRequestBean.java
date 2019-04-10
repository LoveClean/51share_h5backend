package com.scriptures.shareApp.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class MenberChangePwdRequestBean {
//	@ApiModelProperty(value = "id",required=true)
//	private String id;
	@ApiModelProperty(value = "手机号")
	private String phone;
	@ApiModelProperty(value = "新密码")
	private String newPassword;
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
