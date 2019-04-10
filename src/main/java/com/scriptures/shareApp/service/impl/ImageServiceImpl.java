package com.scriptures.shareApp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scriptures.shareApp.contants.Errors;
import com.scriptures.shareApp.controller.request.ImageAddRequestBean;
import com.scriptures.shareApp.controller.request.ImageUpdateRequestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Image;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.mapper.ArticleMapper;
import com.scriptures.shareApp.dao.mapper.CommodityMapper;
import com.scriptures.shareApp.dao.mapper.ImageMapper;
import com.scriptures.shareApp.service.ImageService;
import com.scriptures.shareApp.util.MD5Util;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.util.ResponseEntityUtil;
import com.scriptures.shareApp.util.StringUtil;

@Service
public class ImageServiceImpl implements ImageService {

	@Resource
	private ImageMapper imageMapper;
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private CommodityMapper commodityMapper;

	@Override
	public ResponseEntity<List<Image>> getImage() {
		List<Image> images = imageMapper.selectByNum(5);
		if (images == null || images.size() == 0) {
			return ResponseEntityUtil.fail("目前暂无广告");
		}
		return ResponseEntityUtil.success(images);
	}

	@Override
	public ResponseEntity<Object> selectInfo(String imagename, Integer type) {
		if (type == 1) {
			return ResponseEntityUtil.success(articleMapper.selectByArticleTitle(imagename));
		} else if (type == 2) {
			return ResponseEntityUtil.success(commodityMapper.selectByCategoriesName(imagename));
		} else {
			return ResponseEntityUtil.fail("此类型不存在，请联系管理员");
		}
	}
}
