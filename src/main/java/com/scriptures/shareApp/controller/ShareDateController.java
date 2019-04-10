package com.scriptures.shareApp.controller;


import com.scriptures.shareApp.annotation.ACS;
import com.scriptures.shareApp.controller.request.SDRArticleRequestBean;
import com.scriptures.shareApp.controller.request.SDRCommodityRequestBean;
import com.scriptures.shareApp.service.ShareCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.scriptures.shareApp.service.ShareDateService;
import com.scriptures.shareApp.util.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

@Api(description="分享数据操作接口",produces = "application/json")
@RestController
@RequestMapping("/ShareDate/")
public class ShareDateController {

	@Autowired
	private ShareDateService shareDateService;

	@Autowired
	private ShareCommodityService shareCommodityService;

	@ACS(allowAnonymous = true)
	@ApiOperation(value = "文章分享数据记录",notes = "记录当前浏览用户的ip地址以及分享业务信息")
	@PostMapping(value="articleShareDataRecord.do")
	public ResponseEntity<Object> articleShareDataRecord(@RequestBody SDRArticleRequestBean bean, HttpServletRequest request) {
		return shareDateService.articleShareDataRecord(bean.getShareId(),bean.getShareWay(),request);
	}

	@ACS(allowAnonymous = true)
	@ApiOperation(value = "商品分享数据记录",notes = "记录当前浏览用户的ip地址以及分享业务信息")
	@PostMapping(value="commodityShareDataRecord.do")
	public ResponseEntity<Object> commodityShareDataRecord(@RequestBody SDRCommodityRequestBean bean, HttpServletRequest request) {
		return shareCommodityService.commodityShareDataRecord(bean.getCommodityId(),bean.getMemberId(),bean.getShareWay(),request);
	}


}
