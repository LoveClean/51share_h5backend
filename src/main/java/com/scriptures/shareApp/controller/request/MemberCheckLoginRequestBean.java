package com.scriptures.shareApp.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class MemberCheckLoginRequestBean {
    @ApiModelProperty(value = "用户标识，对当前app唯一")
    private String openId;

    @ApiModelProperty(value = "登录类型，[qq：1] 、[微信：2]、[新浪微博：3]")
    private Integer loginSource;

    @ApiModelProperty(value = "用户的昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像地址 ")
    private String headimgUrl;

    @ApiModelProperty(value = "用户性别 ")
    private Integer sex;


    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(Integer loginSource) {
        this.loginSource = loginSource;

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgUrl() {
        return headimgUrl;
    }

    public void setHeadimgUrl(String headimgUrl) {
        this.headimgUrl = headimgUrl;
    }


}
