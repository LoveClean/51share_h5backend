package com.scriptures.shareApp.controller;

import com.scriptures.shareApp.dao.mapper.SwitchMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.scriptures.shareApp.controller.request.CaptchaRequestBean;
import com.scriptures.shareApp.controller.response.CaptchaResponseBean;
import com.scriptures.shareApp.exception.BusinessException;
import com.scriptures.shareApp.service.MobileCaptchaService;
import com.scriptures.shareApp.annotation.ACS;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.util.ResponseEntityUtil;

/**
 * @Title: CommonController.java
 * @Package com.zhishangquan.server.controller
 * @author liyuchang
 * @Description: 工具公数据controler 如 省市列表
 * @date 2016年11月19日
 * @version V1.0
 */
@Api(value = "公共数据管理", produces = "application/json")
@RestController
@RequestMapping("/common")
public class CommonController {

  @Resource
  private MobileCaptchaService mobileCaptchaService;

  @Resource
  private SwitchMapper switchMapper;
  /**
   * 发送验证码
   * 
   * @param request
   * @return
   */
  @ACS(allowAnonymous = true)
  @ApiOperation(
      value = "发送短信验证码",
      notes = "type类型：1注册,2修改密码,3重置密码,4注册+登陆,5绑定卡<br/>有效时间5分钟，相同类型发送冷却时间1分钟<br/>返回：code=0成功；code=1手机号有误;code=2未超过发送冷却时间，exception=剩余发送冷却时间(单位秒)；code=3送失败请稍后再试")
  @RequestMapping(value = "/sms/sendCaptcha", method = RequestMethod.POST)
  public ResponseEntity<CaptchaResponseBean> sendCaptcha(@Valid @RequestBody CaptchaRequestBean bean) {
    try {
      CaptchaResponseBean result = mobileCaptchaService.send(bean);
      return ResponseEntityUtil.success(result);
    } catch (BusinessException e) {
      return ResponseEntityUtil.fail(e.getCode(), e.getMessage());
    }
  }
  
  
  @ACS(allowAnonymous = true)
  @ApiOperation(value = "aaaaaaa", notes = "xxxxxxx")
  @GetMapping("/audit")
  public boolean auditStatus(){
    boolean auditSwitch = true;
    int aa = switchMapper.selectSwitch();
    if(aa == 0 ){
      auditSwitch = true;
    }else{
      auditSwitch = false;
    }
    return auditSwitch;
  };

}
