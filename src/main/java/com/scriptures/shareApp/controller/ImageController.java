package com.scriptures.shareApp.controller;

import java.util.List;

import com.scriptures.shareApp.annotation.ACS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scriptures.shareApp.controller.request.ImageAddRequestBean;
import com.scriptures.shareApp.controller.request.ImageSelectInfo;
import com.scriptures.shareApp.controller.request.ImageUpdateRequestBean;
import com.scriptures.shareApp.controller.request.MemberAddRequestBean;
import com.scriptures.shareApp.controller.request.ShareAddRequestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Image;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.service.ImageService;
import com.scriptures.shareApp.util.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "轮播图操作接口", produces = "application/json")
@RestController
@RequestMapping("/Image/")
public class ImageController {

	@Autowired
	private ImageService imageService;

	@ACS(allowAnonymous = true)
	@ApiOperation(value = "获取封面轮播图", notes = "5/27：从数据库中获取前五条轮播图数据，不足五条获取全部")
	@GetMapping(value = "getImage.do")
	public ResponseEntity<List<Image>> add() {
		return imageService.getImage();
	}

	@ACS(allowAnonymous = true)
	@ApiOperation(value = "跳转到详情", notes = "跳转到详情")
	@PostMapping(value = "selectInfo.do")
	public ResponseEntity<Object> selectInfo(@RequestBody ImageSelectInfo bean) {
		return imageService.selectInfo(bean.getImagename(), bean.getType());
	}

}
