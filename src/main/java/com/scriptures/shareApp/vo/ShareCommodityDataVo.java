package com.scriptures.shareApp.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ShareCommodityDataVo {
	
	private String phone;
	
	private String ipAddress;
	
	private String shareWay;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getShareWay() {
		return shareWay;
	}

	public void setShareWay(String shareWay) {
		this.shareWay = shareWay;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
