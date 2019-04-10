package com.scriptures.shareApp.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class MemberAddRequestBean {
	@ApiModelProperty(value = "手机号")
	 	private String phone;
	@ApiModelProperty(value = "密码")
	    private String password;
	@ApiModelProperty(value = "昵称")
	    private String nickname;

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
}
