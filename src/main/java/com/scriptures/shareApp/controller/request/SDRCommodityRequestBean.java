package com.scriptures.shareApp.controller.request;

/**
 * 商品分享数据记录
 */
public class SDRCommodityRequestBean {
    String commodityId;
    String memberId;
    Integer shareWay;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getShareWay() {
        return shareWay;
    }

    public void setShareWay(Integer shareWay) {
        this.shareWay = shareWay;
    }
}
