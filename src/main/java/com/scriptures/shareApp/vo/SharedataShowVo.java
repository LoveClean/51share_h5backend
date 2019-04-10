package com.scriptures.shareApp.vo;

import java.util.Date;

public class SharedataShowVo {
	private String id;
	
	private String tpyeName;
	
	private String memberName;
	
	private String typeWay;
	
	private String ipAddress;
	
	private Date clickTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTpyeName() {
		return tpyeName;
	}

	public void setTpyeName(String tpyeName) {
		this.tpyeName = tpyeName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getTypeWay() {
		return typeWay;
	}

	public void setTypeWay(String typeWay) {
		this.typeWay = typeWay;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getClickTime() {
		return clickTime;
	}

	public void setClickTime(Date clickTime) {
		this.clickTime = clickTime;
	}
	
	
 	
}
