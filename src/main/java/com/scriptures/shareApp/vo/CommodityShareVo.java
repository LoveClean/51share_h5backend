package com.scriptures.shareApp.vo;

public class CommodityShareVo {

    private String id;

    private String commodityName;

    private Integer shareTotalCounts;

    private Double shareTotalMoney;

    private Integer shareTotalClick;

    private double turnoverRatio;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getShareTotalCounts() {
        return shareTotalCounts;
    }

    public void setShareTotalCounts(Integer shareTotalCounts) {
        this.shareTotalCounts = shareTotalCounts;
    }

    public Double getShareTotalMoney() {
        return shareTotalMoney;
    }

    public void setShareTotalMoney(Double shareTotalMoney) {
        this.shareTotalMoney = shareTotalMoney;
    }

    public Integer getShareTotalClick() {
        return shareTotalClick;
    }

    public void setShareTotalClick(Integer shareTotalClick) {
        this.shareTotalClick = shareTotalClick;
    }

    public double getTurnoverRatio() {
        return turnoverRatio;
    }

    public void setTurnoverRatio(double turnoverRatio) {
        this.turnoverRatio = turnoverRatio;
    }
}
