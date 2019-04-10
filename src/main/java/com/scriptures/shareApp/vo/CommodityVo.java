package com.scriptures.shareApp.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommodityVo {
	private String id;

	private String cover;

	private String commodityName;

	private String commodityIntroduction;

	private Double commodityPrices;

	private String commodityLink;

	private String commodityCategory;

	private Double commission;

	private String createBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;

	private String updateBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateDate;

	private String remarks;
}
