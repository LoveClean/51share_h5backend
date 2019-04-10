package com.scriptures.shareApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.scriptures.shareApp.controller.request.CommoditycategoriesAddRequestBean;
import com.scriptures.shareApp.controller.request.CommoditycategoriesUptRequestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Commoditycategories;
import com.scriptures.shareApp.service.CommoditycategoriesService;
import com.scriptures.shareApp.service.impl.CommoditycategoriesServiceImpl;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.vo.ArticleVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="商品品类操作接口",produces = "application/json")
@RestController
@RequestMapping("category")
public class CommoditycategoriesController {
	@Autowired 
	private CommoditycategoriesService commoditycategoriesService;
	

	@ApiOperation(value="列表查询所有品类" , notes="分页。")
	@GetMapping("getList.do")
	public PageResponseBean<Commoditycategories> getAll(@RequestParam Integer pageNum ,@RequestParam Integer pageSize) {
		return commoditycategoriesService.getAll(pageNum, pageSize);
 	}
	@ApiOperation(value="根据id查看品类详细信息" , notes="根据id查看品类详细信息。")
	@GetMapping("getInfo.do")
	public ResponseEntity<Object> getInfo(@RequestParam String id) {
		return commoditycategoriesService.getInfo(id);
	}

	
}

