package com.scriptures.shareApp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scriptures.shareApp.contants.Errors;
import com.scriptures.shareApp.controller.request.WithdrawalAddRequestBean;
import com.scriptures.shareApp.controller.request.WithdrawalPageRequestBean;
import com.scriptures.shareApp.controller.request.WithdrawalSearchRequestBean;
import com.scriptures.shareApp.controller.request.WithdrawalUpdateRequestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.entity.Withdrawal;
import com.scriptures.shareApp.dao.mapper.MemberMapper;
import com.scriptures.shareApp.dao.mapper.WithdrawalMapper;
import com.scriptures.shareApp.service.WithdrawalService;
import com.scriptures.shareApp.util.DateUtil;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.util.ResponseEntityUtil;
import com.scriptures.shareApp.util.StringUtil;
import com.scriptures.shareApp.vo.WithdrawalRecordVo;

import javax.annotation.Resource;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

	@Resource
	private WithdrawalMapper withdrawalMapper;
	@Resource
	private MemberMapper memberMapper;

	// 提现
	@Override
	public ResponseEntity<Object> application(String id, String money, String alipayAccount) {
		// 获取当前会员信息：余额
		Member member = memberMapper.selectById(id);
		if (member == null) {
			return ResponseEntityUtil.fail("会员状态异常");
		}
		Double money1;
		try {
			money1 = Double.parseDouble(money);
		} catch (Exception e) {
			return ResponseEntityUtil.fail("参数错误");
		}
		// 校验提现金额与余额
		if (money1 > member.getAvailableBalance()) {
			return ResponseEntityUtil.fail("余额不足");
		}
		//扣钱
		Member member2=new Member();
		member2.setId(member.getId());
		member2.setAvailableBalance(member.getAvailableBalance()-money1);
		member2.setUpdateBy("用户提现");
		member2.setUpdateDate(DateUtil.getCurrentTime());
		int resultCount1=memberMapper.updateByPrimaryKeySelective(member2);
		if(resultCount1==0) {
			return ResponseEntityUtil.fail("扣款失败。");
		}
		// 创建提现申请
		Withdrawal withdrawal=new Withdrawal();
		withdrawal.setId(StringUtil.uuidNotLine());
		withdrawal.setWithdrawal(money1);
		withdrawal.setStatus(0);
		withdrawal.setAlipayAccount(member.getAlipayAccount());
		withdrawal.setAlipayName(member.getAlipayName());
		withdrawal.setCreateBy(member.getPhone());
		withdrawal.setCreateDate(DateUtil.getCurrentTime());
		withdrawal.setRemarks(member.getNickname());
		int resultCount= withdrawalMapper.insertSelective(withdrawal);
		if(resultCount==0) {
			return ResponseEntityUtil.fail("提现出现异常，请联系管理员");
		}
		return ResponseEntityUtil.success("申请提现成功");
	}

	//查看我的提现记录
	@Override
	public ResponseEntity<List<WithdrawalRecordVo>> getRecord(String memberPhone) {
		List<Withdrawal> withdrawals=new ArrayList<>();
		withdrawals=withdrawalMapper.selectRecordByPhone(memberPhone);
		if(withdrawals==null||withdrawals.size()==0) {
			return ResponseEntityUtil.fail("您还未进行过提现");
		}
		List<WithdrawalRecordVo> withdrawalRecordVos=new ArrayList<>();
		for(Withdrawal withdrawal : withdrawals) {
			withdrawalRecordVos.add(createWithdrawalsVo(withdrawal));
		}
		return ResponseEntityUtil.success(withdrawalRecordVos);
	}
	
	private WithdrawalRecordVo createWithdrawalsVo(Withdrawal withdrawal) {
		WithdrawalRecordVo withdrawalRecordVo=new WithdrawalRecordVo();
		withdrawalRecordVo.setId(withdrawal.getId());
		withdrawalRecordVo.setWithdrawal(withdrawal.getWithdrawal());
		withdrawalRecordVo.setStatus(withdrawal.getStatus());
		withdrawalRecordVo.setCreateDate(withdrawal.getCreateDate());
		withdrawalRecordVo.setAlipayAccount(withdrawal.getAlipayAccount());
		return withdrawalRecordVo;
	}
}
