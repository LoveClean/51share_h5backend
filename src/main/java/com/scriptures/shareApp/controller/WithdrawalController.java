package com.scriptures.shareApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scriptures.shareApp.util.ResponseEntityUtil;
import com.scriptures.shareApp.vo.WithdrawalRecordVo;
import com.sun.swing.internal.plaf.metal.resources.metal;
import com.scriptures.shareApp.controller.request.WithdrawalAddRequestBean;
import com.scriptures.shareApp.controller.request.WithdrawalPageRequestBean;
import com.scriptures.shareApp.controller.request.WithdrawalSearchRequestBean;
import com.scriptures.shareApp.controller.request.WithdrawalUpdateRequestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.entity.Withdrawal;
import com.scriptures.shareApp.service.MemberService;
import com.scriptures.shareApp.service.WithdrawalService;
import com.scriptures.shareApp.util.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="佣金返利操作接口",produces = "application/json")
@RestController
@RequestMapping("/Withdrawal/")
public class WithdrawalController extends BaseController {
	
	@Autowired
	private WithdrawalService withdrawalService;
	@Autowired
	private MemberService memberService;
	
	@ApiOperation(value = "用户提现操作接口",notes = "用户提现：已登录的用户输入提现金额检查支付宝账号后申请提现")
	@PostMapping(value="application.do")
	public ResponseEntity<Object> application(@RequestParam String money,@RequestParam String alipayAccount,
												@RequestParam String memberId) {
		ResponseEntity<Member> member=memberService.getInfoById(memberId);
    	if(!member.getData().getAlipayAccount().equals(alipayAccount)) {
    		return ResponseEntityUtil.fail("当前操作账号不是请您的支付宝账户，请确认信息");
    	}
    	ResponseEntity<Object> response=withdrawalService.application(memberId, money, alipayAccount);
    	
		return response;
	}
	
	@ApiOperation(value = "用户查看提现记录",notes = "用户查看自己的提现记录")
	@PostMapping(value="getRecord.do")
	public ResponseEntity<List<WithdrawalRecordVo>> getRecord(HttpServletRequest request) {
		Member member=super.getLoginMember(request);
    	if(member==null) {
    		return ResponseEntityUtil.fail("用户未登录，无法获取信息");
    	}
		return withdrawalService.getRecord(member.getPhone());
	}
	
}
