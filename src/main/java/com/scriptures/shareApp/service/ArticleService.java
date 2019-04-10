package com.scriptures.shareApp.service;

import java.util.List;

import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Article;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.vo.ArticleVo;
import com.scriptures.shareApp.vo.newShare;

public interface ArticleService {




	ResponseEntity<Article> getArticleInfo(String id);

	PageResponseBean<newShare> getArticleGroupByTypeId(String memberId,int pageNum, int pageSize);

//	ResponseEntity<List<ArticleVo>> getListAllByStatus(String memberLabel,int memberCreateDay);
	ResponseEntity<List<ArticleVo>> getListAllByStatus(Member member);

	ResponseEntity<Article> getArticleByShareId(String shareId);
}
