package com.scriptures.shareApp.controller;


import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.scriptures.shareApp.contants.MemberStatusEnum;
import com.scriptures.shareApp.controller.request.*;
import com.scriptures.shareApp.dao.entity.Withdrawal;
import com.scriptures.shareApp.dao.mapper.MemberMapper;
import com.scriptures.shareApp.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scriptures.shareApp.annotation.ACS;
import com.scriptures.shareApp.contants.Errors;
import com.scriptures.shareApp.contants.SmsConstants.SmsCaptchaType;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.service.MemberService;
import com.scriptures.shareApp.service.MobileCaptchaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "会员人员操作接口", produces = "application/json")
@RestController
@RequestMapping("/Member/")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MobileCaptchaService mobileCaptchaService;


    
    @ApiOperation(value = "判断用户提现次数", notes = "72小时内只能提现一次")
    @PostMapping(value = "clock.do")
    public ResponseEntity clock(@RequestParam String phone) {
        return memberService.clock(phone);
    }

    
    @ACS(allowAnonymous = true)
    @ApiOperation(value = "会员登录接口", notes = "会员根据手机号与密码登录")
    @PostMapping(value = "login.do")
    public ResponseEntity<Member> login(@RequestBody MemberLoginRequestBean bean, HttpServletRequest request) {
        ResponseEntity<Member> response = memberService.login(bean);
        if (response.isSuccess()) {
            Member member = response.getData();
            //session.setAttribute(Const.CURRENT_USER, response.getData());
            // 创建访问token
            String accessToken = super.generateAccessToken(request);
            member.setAccessToken(accessToken);

            super.setAccessTokenAttribute(request, accessToken);
            super.sessionMember(request, member);

            return ResponseEntityUtil.success(member);
        }
        return response;
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "第三方登录接口", notes = "")
    @PostMapping(value = "checkLogin.do")
    public ResponseEntity<Member> checkLogin(@RequestBody  MemberCheckLoginRequestBean bean , HttpServletRequest request){
        Member user=memberService.checkLogin(bean.getOpenId());
        if(user==null){
            //首次登录
            
            Member member = new Member();
            Date now= DateUtil.getCurrentTime();
            String id=StringUtil.uuidNotLine();
            if(memberService.checkNickname(bean.getNickname())) {
            	member.setNickname("用户"+id.substring(0,9));
            }else {
            	member.setNickname(bean.getNickname());
            }
            member.setId(id);
            member.setOpenId(bean.getOpenId());
            member.setIcon(bean.getHeadimgUrl());
            member.setLoginSource(bean.getLoginSource());
            member.setSex(bean.getSex());
            member.setCreateBy(bean.getOpenId());
            member.setCreateDate(now);
            member.setUpdateDate(now);
          

            if(memberService.checkRegister(member)>0){
                //第三方信息注册成功，登录
                // 创建访问token
                String accessToken = super.generateAccessToken(request);
                member.setAccessToken(accessToken);
                super.setAccessTokenAttribute(request, accessToken);
                super.sessionMember(request, member);
                return ResponseEntityUtil.success(member);
            }else {
                return ResponseEntityUtil.fail("登录失败");
            }
        }else if(user.getDelFlag()==MemberStatusEnum.STOP.getStatus()){
        	 return ResponseEntityUtil.fail(Errors.USER_STOP_ILLEGAL.label);
        }else{
            //登录
            // 创建访问token
            String accessToken = super.generateAccessToken(request);
            user.setAccessToken(accessToken);
            user.setPassword(null);
            super.setAccessTokenAttribute(request, accessToken);
            super.sessionMember(request, user);
            return ResponseEntityUtil.success(user);
        }
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "第三方账号绑定手机号", notes = "第三方账号绑定手机号 参数：验证码 手机号 密码  标识：openid memberid ")
    @PostMapping(value = "PhoneBinding.do")
    public ResponseEntity PhoneBinding(@RequestBody PhoneBindingRequestBean bean,HttpServletRequest request) {
    	 if (bean == null || bean.getPhone() == null) {
             return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
         }
         String reg2 = "^[A-Za-z]+[0-9]+[A-Za-z0-9]*|[0-9]+[A-Za-z]+[A-Za-z0-9]*$";
         if (!bean.getPassword().matches(reg2)) {
             return ResponseEntityUtil.fail("密码必须包含字母与数字！");
         }
         if (!(bean.getPassword().length() > 6 && bean.getPassword().length() <= 12)) {
             return ResponseEntityUtil.fail("密码长度要在7-12个字符！");
         }
         String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
         if (!bean.getPhone().matches(PHONE_NUMBER_REG)) {
             return ResponseEntityUtil.fail("请输入正确的手机号格式！");
         }
    	if(!mobileCaptchaService.verify(bean.getPhone(),bean.getCaptcha(), SmsCaptchaType.REGIST)){
            return ResponseEntityUtil.fail("验证码错误");
        }
        ResponseEntity<String> response=memberService.PhoneBinding(bean);
        if (response.isSuccess()) {
            Member member = memberService.checkLogin(bean.getOpenId());
            if (member==null){
                return ResponseEntityUtil.fail("不存在该openid的用户");
            }
            //session.setAttribute(Const.CURRENT_USER, response.getData());
            // 创建访问token
            String accessToken = super.generateAccessToken(request);
            member.setAccessToken(accessToken);

            super.setAccessTokenAttribute(request, accessToken);
            super.sessionMember(request, member);

            return ResponseEntityUtil.success(member);
        }
        return response;
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "会员注册接口", notes = "会员使用手机号注册")
    @PostMapping(value = "register.do")
    public ResponseEntity<String> add(@RequestBody MemberAddRequestBean bean) {
        return memberService.add(bean);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "校验验证码(注册)", notes = "会员注册验证码验证")
    @PostMapping(value = "registerVerify.do")
    public ResponseEntity<Boolean> verify(@RequestBody MobileVerifyRequestBean bean) {
        return ResponseEntityUtil.success(mobileCaptchaService.verify(bean.getMobile(), bean.getCaptcha(),SmsCaptchaType.REGIST));
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "会员注册接口2", notes = "验证码与信息一起提交")
    @PostMapping(value = "register2.do")
    public ResponseEntity<String> regist(@RequestBody MemberRegistRequestBean bean) {
        if(!mobileCaptchaService.verify(bean.getPhone(),bean.getCaptcha(), SmsCaptchaType.REGIST)){
            return ResponseEntityUtil.fail("验证码错误");
        }
        MemberAddRequestBean  memberAddRequestBean=new MemberAddRequestBean();
        memberAddRequestBean.setPhone(bean.getPhone());
        memberAddRequestBean.setNickname(bean.getNickname());
        memberAddRequestBean.setPassword(bean.getPassword());
        return memberService.add(memberAddRequestBean);
    }
    
    @ApiOperation(value = "会员退出接口", notes = "已登录的会员登出")
    @PostMapping(value = "loginout.do")
    public ResponseEntity<Member> loginOut(HttpServletRequest request) {
    	deleteSessionMember(request);
        return ResponseEntityUtil.success();
    }
    
    @ApiOperation(value = "获取会员信息，根据id", notes = "根据id获取会员的信息")
    @GetMapping(value = "getInfoById.do")
    public ResponseEntity<Member> getMemberInfoById(@RequestParam String id) {
    	return memberService.getInfoById(id);
    }
    
    @ApiOperation(value = "获取会员信息", notes = "获取当前登录会员的信息")
    @GetMapping(value = "getInfo.do")
    public ResponseEntity<Member> getMemberInfo(HttpServletRequest request) {
    	Member member=super.getLoginMember(request);
    	Member member2=memberService.getInfoById(member.getId()).getData();
    	if(member==null) {
    		return ResponseEntityUtil.fail("用户未登录，无法获取信息");
    	}
    	return ResponseEntityUtil.success(member2);
    }
    
    @ApiOperation(value = "会员修改个人信息接口", notes = "会员修改个人信息")
    @PostMapping(value = "update.do")
    public ResponseEntity<Member> getOne(@RequestBody MemberUpdateRequestBean bean) {
        Member member = new Member();
        member.setId(bean.getId());
        member.setAddress(bean.getAddress());
        member.setAlipayAccount(bean.getAlipayAccount());
        member.setAlipayName(bean.getAlipayName());
        member.setIcon(bean.getIcon());
        member.setNickname(bean.getNickname());
        member.setUpdateBy("用户本人");
        member.setSex(bean.getSex());
        member.setUpdateDate(new Date());
        member.setTruename(bean.getTruename());
        return  memberService.update(member);
    }
    
    @ACS(allowAnonymous = true)
    @ApiOperation(value = "会员修改密码", notes = "会员根据旧密码设置新密码")
    @PostMapping(value = "changePwd.do")
    public ResponseEntity<String> changePwd(@RequestBody MenberChangePwdRequestBean bean , HttpServletRequest request) {
        return memberService.changePwd(bean);
    }
    
    @ACS(allowAnonymous = true)
    @ApiOperation(value = "校验验证码(修改密码)", notes = "校验验证码")
    @PostMapping(value = "check.do")
    public ResponseEntity<Boolean> check(@RequestBody MemberCheckRequestBean bean , HttpServletRequest request) {
    		return ResponseEntityUtil.success(mobileCaptchaService.verify(bean.getMobile(), bean.getCaptcha(),SmsCaptchaType.RESET_PWD));
    }
    
    @ACS(allowAnonymous = true)
    @ApiOperation(value = "手机号校验", notes = "检查手机号格式以及是否被注册")
    @PostMapping(value = "checkPhone.do")
    public ResponseEntity<String> checkPhone(@RequestParam String phone) {
    	  String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
          if (!phone.matches(PHONE_NUMBER_REG)) {
              return ResponseEntityUtil.fail("请输入正确的手机号格式！");
          }
          if(memberService.checkPhone(phone)) {
        	  return ResponseEntityUtil.fail("该手机号已经注册");
          }
          return ResponseEntityUtil.success("校验通过");
    }
    
    @ApiOperation(value = "更新头像", notes = "更新头像修改")
    @PostMapping(value = "updateIcon.do")
    public ResponseEntity<String> checkPhone(@RequestBody MemberUpdateIconRequestBean bean) {
          return memberService.updateIcon(bean);
    }
    
    @ACS(allowAnonymous = true)
    @ApiOperation(value = "得到头像", notes = "得到头像")
    @PostMapping(value = "getInfoByPhone.do")
    public ResponseEntity<String> getInfoByPhone(@RequestParam String phone) {
          return memberService.getInfoByPhone(phone);
    }



}
