package com.scriptures.shareApp.vo;

public class ShareDataWithCommodity {

    private String typeId;
    
    private String commodityName;
    
    private String cover;

    private Integer tradedCounts;

    private Double reward;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Integer getTradedCounts() {
		return tradedCounts;
	}

	public void setTradedCounts(Integer tradedCounts) {
		this.tradedCounts = tradedCounts;
	}

	public Double getReward() {
		return reward;
	}

	public void setReward(Double reward) {
		this.reward = reward;
	}
    
    
}
