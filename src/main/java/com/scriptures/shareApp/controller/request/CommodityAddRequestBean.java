package com.scriptures.shareApp.controller.request;

import java.util.Date;

public class CommodityAddRequestBean {

    private String cover;

    private String commodityName;

    private String commodityIntroduction;

    private Double commodityPrices;

    private String commodityLink;

    private String commodityCategory;

    private Double commission;

    private String createBy;

    private String remarks;

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityIntroduction() {
		return commodityIntroduction;
	}

	public void setCommodityIntroduction(String commodityIntroduction) {
		this.commodityIntroduction = commodityIntroduction;
	}

	public Double getCommodityPrices() {
		return commodityPrices;
	}

	public void setCommodityPrices(Double commodityPrices) {
		this.commodityPrices = commodityPrices;
	}

	public String getCommodityLink() {
		return commodityLink;
	}

	public void setCommodityLink(String commodityLink) {
		this.commodityLink = commodityLink;
	}

	public String getCommodityCategory() {
		return commodityCategory;
	}

	public void setCommodityCategory(String commodityCategory) {
		this.commodityCategory = commodityCategory;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    
}
