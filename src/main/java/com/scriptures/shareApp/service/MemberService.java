package com.scriptures.shareApp.service;

import java.util.List;

import com.scriptures.shareApp.controller.request.*;
import org.springframework.stereotype.Service;

import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.github.pagehelper.PageInfo;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.util.ResponseEntity;


public interface MemberService {
	
	ResponseEntity<String> changePwd(MenberChangePwdRequestBean bean);		//手机App
	
	ResponseEntity<Member> login(MemberLoginRequestBean bean);
	
	ResponseEntity<Member> update(Member member);

	ResponseEntity<String> add(MemberAddRequestBean bean);

	int checkRegister(Member member);

	boolean checkPhone(String phone);

	ResponseEntity<Member> getInfoById(String id);
	
	ResponseEntity<String> getInfoByPhone(String phone);

	ResponseEntity<String> updateIcon(MemberUpdateIconRequestBean bean);

    Member checkLogin(String openId);

    ResponseEntity<String> PhoneBinding(PhoneBindingRequestBean bean);

	ResponseEntity clock(String phone);

	boolean  checkNickname(String nickname);
}
