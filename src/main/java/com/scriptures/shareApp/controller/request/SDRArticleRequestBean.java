package com.scriptures.shareApp.controller.request;

/**
 * 文章分享数据记录
 */
public class SDRArticleRequestBean {
     String shareId;
     Integer shareWay;

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public Integer getShareWay() {
        return shareWay;
    }

    public void setShareWay(Integer shareWay) {
        this.shareWay = shareWay;
    }
}
