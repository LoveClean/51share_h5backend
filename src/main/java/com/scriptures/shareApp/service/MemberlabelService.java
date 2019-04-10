package com.scriptures.shareApp.service;

import java.util.List;

import com.scriptures.shareApp.controller.request.MemberAddRequestBean;
import com.scriptures.shareApp.controller.request.MemberUpdateRequestBean;

import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.entity.Memberlabel;
import com.scriptures.shareApp.util.ResponseEntity;

public interface MemberlabelService {
	
	public PageResponseBean<Memberlabel> pageGetAll(Integer pageNum,Integer pageSize);
	
}
