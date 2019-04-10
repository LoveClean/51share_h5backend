package com.scriptures.shareApp.service.impl;

import java.util.Date;
import java.util.List;

import com.scriptures.shareApp.contants.MemberStatusEnum;
import com.scriptures.shareApp.controller.request.*;
import com.scriptures.shareApp.dao.entity.Withdrawal;
import com.scriptures.shareApp.dao.mapper.WithdrawalMapper;
import org.springframework.stereotype.Service;

import com.scriptures.shareApp.contants.Errors;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.mapper.MemberMapper;
import com.scriptures.shareApp.service.MemberService;
import com.scriptures.shareApp.util.DateUtil;
import com.scriptures.shareApp.util.MD5Util;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.util.ResponseEntityUtil;
import com.scriptures.shareApp.util.StringUtil;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;
    @Resource
    private WithdrawalMapper withdrawalMapper;

    @Override
    public ResponseEntity<Member> update(Member member) {
    	if ( member== null) {
            return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
        }
        String reg = "^[\\u4E00-\\u9FFF]+$";
        if(!StringUtil.isEmpty(member.getTruename())&&!member.getTruename().matches(reg)) {
        	return ResponseEntityUtil.fail("真实姓名只能为汉字！");
        }
        
        Integer checkCount = memberMapper.checkNicknameUpt(member.getNickname(),member.getId());
        if(checkCount>0) {
        	return ResponseEntityUtil.fail("该昵称已存在");
        }
        if(!StringUtil.isEmpty(member.getAlipayAccount())) {
        	 String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        	String EMAIL_REG="^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        	if(!member.getAlipayAccount().matches(PHONE_NUMBER_REG) && !member.getAlipayAccount().matches(EMAIL_REG)) {
        		return ResponseEntityUtil.fail("请输入正确的支付宝账号");
        	}
        }
      
        int keyCount = memberMapper.updateByPrimaryKeySelective(member);
        if (keyCount > 0) {
            return ResponseEntityUtil.success(member);
        }
        return ResponseEntityUtil.fail("更新失败！");
    }

    @Override
    public ResponseEntity<String> add(MemberAddRequestBean bean) {
        if (bean == null || bean.getPhone() == null) {
            return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
        }
        String reg2 = "^[A-Za-z]+[0-9]+[A-Za-z0-9]*|[0-9]+[A-Za-z]+[A-Za-z0-9]*$";
        if (!bean.getPassword().matches(reg2)) {
            return ResponseEntityUtil.fail("密码必须包含字母与数字！");
        }
        if (!(bean.getPassword().length() > 6 && bean.getPassword().length() <= 12)) {
            return ResponseEntityUtil.fail("密码长度要在7-12个！");
        }
        if (checkPhone(bean.getPhone())) {
            return ResponseEntityUtil.fail("该手机号已被注册！");
        }
        if (checkNickname(bean.getNickname())) {
            return ResponseEntityUtil.fail("该昵称已存在！");
        }
        String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        if (!bean.getPhone().matches(PHONE_NUMBER_REG)) {
            return ResponseEntityUtil.fail("请输入正确的手机号格式！");
        }
        Member member = new Member();
        member.setId(StringUtil.uuidNotLine());
        member.setPassword(MD5Util.MD5(bean.getPassword()));
        member.setNickname(bean.getNickname());
        member.setCreateBy("用户注册");
        member.setSex(1);
        member.setCreateDate(new Date());
        member.setPhone(bean.getPhone());
        int keyCount = memberMapper.insertSelective(member);
        if (keyCount > 0) {
            return ResponseEntityUtil.success("注册成功！");
        }
        return ResponseEntityUtil.fail("注册失败！");
    }
    //第三方注册
    @Override
    public int checkRegister(Member member){
    	member.setDelFlag(MemberStatusEnum.NOBINDINGPHONE.getStatus());
        return memberMapper.insertSelective(member);
    }
    //第三方账号绑定手机号
    @Override
    public ResponseEntity<String> PhoneBinding(PhoneBindingRequestBean bean){
        Member member=memberMapper.selectByOpenId(bean.getOpenId());
        if(member==null){
            return ResponseEntityUtil.fail("绑定失败");
        }
        if(!StringUtil.isEmpty(member.getPhone())){
            return ResponseEntityUtil.fail("该用户已经绑定手机号，不可重复绑定，不可修改");
        }
        member.setPhone(bean.getPhone());
        member.setPassword(MD5Util.MD5(bean.getPassword()));
        member.setDelFlag(MemberStatusEnum.NORMAL.getStatus());
        //更新
        int resultCount=memberMapper.updateByPrimaryKeySelective(member);
        return ResponseEntityUtil.updMessage(resultCount);
    }

    @Override
    public ResponseEntity clock(String phone) {
        List<Withdrawal> withdrawals=withdrawalMapper.selectRecordByPhone(phone);
        if (withdrawals.size()==0) {
        	return ResponseEntityUtil.success(true);
		}
        Withdrawal withdrawal=withdrawals.get(0);
        Long date=withdrawal.getCreateDate().getTime();
        Long currentTime=System.currentTimeMillis();
        Double val=(double)(currentTime-date)/3600000;
        if (val>72){
            return ResponseEntityUtil.success(true);
        }

        return ResponseEntityUtil.success(false);

    }

    @Override
    public boolean checkPhone(String phone) {
		int count=memberMapper.checkPhone(phone);
		if(count>0) {
			return true;//存在
		}
		return false;//不存在
	}

	@Override
    public ResponseEntity<String> changePwd(MenberChangePwdRequestBean bean) {
        if (bean == null) {
            return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
        }
        String reg2 = "^[A-Za-z]+[0-9]+[A-Za-z0-9]*|[0-9]+[A-Za-z]+[A-Za-z0-9]*$";
        if (!bean.getNewPassword().matches(reg2)) {
            return ResponseEntityUtil.fail("密码必须包含字母与数字！");
        }
        if (!(bean.getNewPassword().length() > 6 && bean.getNewPassword().length() <= 12)) {
            return ResponseEntityUtil.fail("密码长度要在7-12个！");
        }
        Member member = memberMapper.selectByPhone(bean.getPhone());
        if (member == null) {
            return ResponseEntityUtil.fail("该会员不存在或者已被删除！");
        }
//        if (!member.getPassword().equals(MD5Util.MD5(bean.getOldPassword()))) {
//            return ResponseEntityUtil.fail("旧密码错误！");
//        }
        member.setPassword(MD5Util.MD5(bean.getNewPassword()));
        member.setUpdateBy("用户本人");
        member.setUpdateDate(DateUtil.getCurrentTime());
        int keyCount = memberMapper.updateByPrimaryKeySelective(member);
        if (keyCount > 0) {
            return ResponseEntityUtil.success("修改密码成功！");
        }
        return ResponseEntityUtil.fail("修改密码失败！");
    }
  
    @Override
    public ResponseEntity<Member> login(MemberLoginRequestBean bean) {
        if (bean == null) {
            return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
        }
        
        Member member = memberMapper.login(bean.getPhone(), MD5Util.MD5(bean.getPassword()));
        
        if (member == null) {
            return ResponseEntityUtil.fail(Errors.USER_LOGIN_ERROR.label);
        }
        if(member.getDelFlag() == MemberStatusEnum.STOP.getStatus()){
            return ResponseEntityUtil.fail(Errors.USER_STOP_ILLEGAL.label);
        }
        member.setPassword(null);
        return ResponseEntityUtil.success(member);

    }

    //第三方登录根据openid查找会员
    @Override
    public Member checkLogin(String openId){
        return memberMapper.selectByOpenId(openId);
    }

    @Override
    public boolean checkNickname(String nickname) {
        Member member = memberMapper.checkNickname(nickname);
        if (member == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public ResponseEntity<Member> getInfoById(String id) {
    	Member member=memberMapper.selectById(id);
    	if(member==null) {
    		return ResponseEntityUtil.fail("没有找到数据");
    	}
    	member.setPassword(null);
    	return ResponseEntityUtil.success(member);
    }

    
    @Override
	public ResponseEntity<String> updateIcon(MemberUpdateIconRequestBean bean) {
		 if (bean == null) {
	            return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
	        }
		Member member=memberMapper.selectById(bean.getId());
    	if(member==null) {
    		return ResponseEntityUtil.fail("没有该用户信息！");
    	}
    	member.setId(bean.getId());
    	member.setIcon(bean.getIcon());
    	int keyCount=memberMapper.updateByPrimaryKeySelective(member);
    	if (keyCount>0) {
    		return ResponseEntityUtil.success("头像修改成功！");
		}else {
			return ResponseEntityUtil.fail("头像修改失败！");
		}
    	
	}

	@Override
	public ResponseEntity<String> getInfoByPhone(String phone) {
		if (phone == null) {
            return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
        }
		Member member = memberMapper.selectByPhone(phone);
		if(member==null) {
    		return ResponseEntityUtil.fail("没有该用户信息！");
    	}
		return ResponseEntityUtil.success(member.getIcon());
	}
	
}
