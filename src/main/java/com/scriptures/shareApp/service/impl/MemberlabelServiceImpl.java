package com.scriptures.shareApp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scriptures.shareApp.contants.Errors;
import com.scriptures.shareApp.controller.request.MemberAddRequestBean;
import com.scriptures.shareApp.controller.request.MemberUpdateRequestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.entity.Memberlabel;
import com.scriptures.shareApp.dao.mapper.MemberlabelMapper;
import com.scriptures.shareApp.service.MemberlabelService;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.util.ResponseEntityUtil;
import com.scriptures.shareApp.util.StringUtil;

@Service
public class MemberlabelServiceImpl implements MemberlabelService{

	@Autowired
	private MemberlabelMapper memberlabelMapper;

	@Override
	public PageResponseBean<Memberlabel> pageGetAll(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Memberlabel> memberlabels=(List<Memberlabel>)memberlabelMapper.selectAll();
		PageInfo<Memberlabel> pageInfo=new PageInfo<>();
		pageInfo.setList(memberlabels);
		int nums=memberlabelMapper.selectAll().size();
		int totalPage=nums%pageSize==0?nums/pageSize:nums/pageSize+1;
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(nums);
		pageInfo.setPages(totalPage);
		
		PageResponseBean<Memberlabel> page=new PageResponseBean<Memberlabel>(pageInfo);
		page.setCode(0);
		page.setHttpStatus(1);
		return page;
	}

	
	
	private boolean checkLabelName(String name) {
		Memberlabel memberlabel=memberlabelMapper.checkName(name);
		if (memberlabel==null) {
			return false;
		}
		return true;
	}

	
	
}
