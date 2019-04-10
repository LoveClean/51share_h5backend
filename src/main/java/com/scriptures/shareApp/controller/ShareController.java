package com.scriptures.shareApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scriptures.shareApp.contants.Const;
import com.scriptures.shareApp.contants.Errors;
import com.scriptures.shareApp.controller.request.ShareAddRequestBean;
import com.scriptures.shareApp.controller.request.SharePageSearchRequestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.entity.Share;
import com.scriptures.shareApp.service.ShareService;
import com.scriptures.shareApp.util.HttpUtil;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="分享操作接口",produces = "application/json")
@RestController
@RequestMapping("/newShare/")
public class ShareController extends  BaseController{

	@Autowired
	private ShareService shareService;

	@ApiOperation(value = "分享文章",notes = "拿到文章id与会员id拼装url，分享")
	@PostMapping(value="articleShare.do")
	public ResponseEntity<String> articleShare(@RequestParam String articleId ,HttpServletRequest request) {
		Member member = super.getLoginMember(request);
		if(member==null){
			return ResponseEntityUtil.fail("请先登录");
		}
		return shareService.shareArticle(member.getId(),member.getPhone(),articleId );
	}

	@ApiOperation(value = "分享商品",notes = "拿到文章id与会员id拼装url，分享")
	@PostMapping(value="commodityShare.do")
	public ResponseEntity<String> commodityShare(@RequestParam String commodityId ,HttpServletRequest request) {
		Member member = super.getLoginMember(request);
		if(member==null){
			return ResponseEntityUtil.fail("请先登录");
		}
		return shareService.shareCommodity(member.getId(),commodityId );
	}
	
}
