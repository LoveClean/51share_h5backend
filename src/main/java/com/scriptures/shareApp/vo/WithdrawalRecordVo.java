package com.scriptures.shareApp.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WithdrawalRecordVo {
	private String id;

	private Double withdrawal;

	private Integer status;

	private String alipayAccount;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(Double withdrawal) {
		this.withdrawal = withdrawal;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	

}
