package com.scriptures.shareApp.controller;

import com.scriptures.shareApp.annotation.ACS;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.util.DateUtil;
import com.scriptures.shareApp.vo.ArticleVo;
import com.scriptures.shareApp.vo.newShare;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Article;
import com.scriptures.shareApp.service.ArticleService;
import com.scriptures.shareApp.service.MemberService;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

@Api(description="文章操作接口",produces = "application/json")
@RestController
@RequestMapping("article")
public class ArticleController extends BaseController {
	@Autowired
	private ArticleService articleService;
	@Autowired
    private MemberService memberService;


//	@ACS(allowAnonymous = true)
//	@ApiOperation(value="查询可分享文章" , notes="分页，只查询可以分享的文章")
//	@GetMapping("getAllByStatus")
//	public ResponseEntity<List<ArticleVo>> getAllByStatus(HttpServletRequest request) {
//		Member member=super.getLoginMember(request);
//		if (member==null){
//			System.out.println("用户未登录");
//			return articleService.getListAllByStatus(null,0);
//		}
//		int temp=DateUtil.daysBetween(member.getCreateDate(),new Date());
//		System.out.println(temp);
//		return articleService.getListAllByStatus(member.getLabel(),temp);
//	}

	@ACS(allowAnonymous = true)
	@ApiOperation(value="查询可分享文章" , notes="分页，只查询可以分享的文章")
	@GetMapping("getAllByStatus")
	public ResponseEntity<List<ArticleVo>> getAllByStatus(HttpServletRequest request) {
		Member member=super.getLoginMember(request); 
		Member member2;
		if(member==null) {
			member2=null;
		}else {
			member2=memberService.getInfoById(member.getId()).getData();
		}
		
		return articleService.getListAllByStatus(member2);
	}


	@ACS(allowAnonymous = true)
	@ApiOperation(value="根据id查看文章详细信息" , notes="根据id查看文章详细信息")
	@GetMapping("getInfo")
	public ResponseEntity<Article> getArticleInfo(@RequestParam String id) {
		return articleService.getArticleInfo(id);
	} 

	
	@ApiOperation(value="查看我分享的文章" , notes="根据id查看分享的文章记录")
	@GetMapping("getArticleGroupById")
	public PageResponseBean<newShare> getArticleGroupById(@RequestParam String memberId,@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
		return articleService.getArticleGroupByTypeId(memberId,pageNum,pageSize);
	}


}
