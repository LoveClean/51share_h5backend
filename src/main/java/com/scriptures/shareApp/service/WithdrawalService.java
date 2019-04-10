package com.scriptures.shareApp.service;

import java.util.List;

import com.scriptures.shareApp.controller.request.WithdrawalAddRequestBean;
import com.scriptures.shareApp.controller.request.WithdrawalPageRequestBean;
import com.scriptures.shareApp.controller.request.WithdrawalSearchRequestBean;
import com.scriptures.shareApp.controller.request.WithdrawalUpdateRequestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Withdrawal;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.vo.WithdrawalRecordVo;

public interface WithdrawalService {

	ResponseEntity<Object> application(String id, String money, String alipayAccount);

	ResponseEntity<List<WithdrawalRecordVo>> getRecord(String memberId);
	
	
}
