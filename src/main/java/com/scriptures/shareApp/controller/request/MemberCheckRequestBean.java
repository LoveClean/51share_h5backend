package com.scriptures.shareApp.controller.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.scriptures.shareApp.contants.SmsConstants;

import io.swagger.annotations.ApiModelProperty;

public class MemberCheckRequestBean {
	@ApiModelProperty(value = "手机号", required = true)
	  @NotBlank(message = "手机号不能为空")
	  private String mobile;
	
	@ApiModelProperty(value = "验证码", required = true)
	  @NotNull(message = "验证码不能为空")
	  private String captcha;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	  
	  
}
