package com.scriptures.shareApp.controller;

import com.scriptures.shareApp.annotation.ACS;
import com.scriptures.shareApp.dao.entity.Article;
import com.scriptures.shareApp.service.ArticleService;
import com.scriptures.shareApp.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description="游客操作接口",produces = "application/json")
@RestController
@RequestMapping("visitor")
public class visitorController {
    @Autowired
    private ArticleService articleService;

    @ACS(allowAnonymous = true)
    @ApiOperation(value="游客访问查看文章" , notes="根据分享id查看文章")
    @GetMapping("getInfo")
    public ResponseEntity<Article> getArticleInfo(@RequestParam String shareId) {
        return articleService.getArticleByShareId(shareId);
    }
}
