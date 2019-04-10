package com.scriptures.shareApp.vo;

public class ArticleShareVo {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String articleTitle;

    private Double shareTotalMoney;

    private Integer shareTotalClick;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
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
}
