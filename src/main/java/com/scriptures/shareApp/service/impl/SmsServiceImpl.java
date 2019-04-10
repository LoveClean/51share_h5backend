package com.scriptures.shareApp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.scriptures.shareApp.config.SmsConfig;
import com.scriptures.shareApp.contants.Errors;
import com.scriptures.shareApp.contants.SmsConstants;
import com.scriptures.shareApp.service.SmsService;
import com.scriptures.shareApp.util.AliYunSmsUtil;
import com.scriptures.shareApp.util.ExceptionUtil;
import com.scriptures.shareApp.util.HttpUtil;
import com.scriptures.shareApp.util.StringUtil;

/**
 * @Title: SmsServiceImpl.java
 * @Package cc.uworks.library.service.impl
 * @author liyuchang
 * @date 2017年1月23日
 * @version V1.0
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {

  private Logger logger = LoggerFactory.getLogger(getClass());
  @Resource
  private SmsConfig smsConfig;
  
  @Autowired
  private AliYunSmsUtil aliyunSms;

  /**
   * 短信单发
   * 
   * @param mobile
   * @param content
   * @return true 发送成功，false 发送失败
   */
  @Override
  public boolean send(String mobile, String content) {
    try {
      logger.info("发送短信, mobile = {}, content = {}", mobile, content);
      if (StringUtil.isBlank(mobile)) {
        ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "手机号不能为空");
      }
      if (StringUtil.isBlank(content)) {
        ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "短信内容不能为空");
      }
      JSONObject params = new JSONObject();
      params.put("code", content);
      
     
      
      aliyunSms.sendMsg(mobile, params.toJSONString());
      return true;
    } catch (Exception e) {
      logger.error("发送短信失败， mobile = {}, content = {}, error={}", mobile, content, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 短信群发
   * 
   * @param mobiles
   * @param content
   */
  @Override
  public void send(List<String> mobiles, String content) {
   /* StringBuilder mobilesStr = new StringBuilder();
    mobiles.forEach(mobile -> {
      if (StringUtils.isNotBlank(mobile)) {
        mobilesStr.append(mobile).append(",");
      }
      if (mobiles.toString().split(",").length >= SmsConstants.MAX_COUNT) {
        this.send(mobiles.toString().replaceAll(",$", ""), content);
        mobilesStr.setLength(0);
      }
    });
    if (mobilesStr.length() > 0) {
      this.send(mobiles.toString().replaceAll(",$", ""), content);
    }*/
  }
  
 


}
