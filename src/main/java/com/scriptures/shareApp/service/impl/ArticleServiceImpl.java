package com.scriptures.shareApp.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.mapper.ShareMapper;
import com.scriptures.shareApp.util.*;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Article;
import com.scriptures.shareApp.dao.entity.Share;
import com.scriptures.shareApp.dao.mapper.ArticleMapper;
import com.scriptures.shareApp.service.ArticleService;
import com.scriptures.shareApp.vo.ArticleShareVo;
import com.scriptures.shareApp.vo.ArticleVo;
import com.scriptures.shareApp.vo.newShare;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ShareMapper shareMapper;


	//查看可分享的文章，分页
	@Override
	public ResponseEntity<List<ArticleVo>> getListAllByStatus(Member member) {
		List<Article> articles = articleMapper.selectAllByStatus();
		List<ArticleVo> articleVos = new ArrayList<>();
		if (articles != null && articles.size() > 0) {
			//未登录
			if(member==null){
				System.out.println("会员未登录");
				for (Article article : articles) {
					if(StringUtil.isEmpty(article.getLabel())){
						articleVos.add(createArticleVo(article));
					}
				}
			}else {
				//会员已登录
				int memberCreateDay=DateUtil.daysBetween(member.getCreateDate(),new Date());
				//System.out.println(memberCreateDay);
				for (Article article : articles) {
					String aL=article.getLabel();
					if(StringUtil.isEmpty(aL)){
						articleVos.add(createArticleVo(article));
					}else {
						//截取动态标签
						int b=aL.indexOf("#");
						int a=aL.lastIndexOf("#");
						if(b!=-1&&a!=-1){
							int day=Integer.parseInt(aL.substring(b+1,a));
							//System.out.println(day);
							if(memberCreateDay<=day){
								articleVos.add(createArticleVo(article));
								continue;
							}
						}
						//判断是否有交集
						if(ListUtil.checkLabel(member.getLabel(),aL)){
							articleVos.add(createArticleVo(article));
						}
					}
				}
			}
			return ResponseEntityUtil.success(articleVos);
		}
		return ResponseEntityUtil.fail("没有可见文章");
	}
 
    @Override
    public ResponseEntity<Article> getArticleInfo(String id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            return ResponseEntityUtil.fail("没有查询到相关文章");
        }
        return ResponseEntityUtil.success(article);
    }

    //根据会员id查看文章分享信息
	@Override
	public PageResponseBean<newShare> getArticleGroupByTypeId(String memberId,int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<newShare> newShares=shareMapper.selectGroupByTypeIdOfArticle(memberId);
		PageInfo<newShare> pageInfo = new PageInfo<>(newShares);
		PageResponseBean<newShare> pageResponseBean = new PageResponseBean<>(pageInfo);
		pageResponseBean.setCode(0);
		pageResponseBean.setHttpStatus(200);
		return pageResponseBean;
	}

	 private ArticleVo createArticleVo(Article article) {
	        ArticleVo articleVo = new ArticleVo();
	        if (article != null) {
	            articleVo.setId(article.getId());
	            articleVo.setCover(article.getCover());
	            articleVo.setAuthor(article.getAuthor());
	            articleVo.setArticleTitle(article.getArticleTitle());
	            articleVo.setArticleContent(article.getArticleContent());
	            articleVo.setCommission(article.getCommission());
	            articleVo.setRemarks(article.getRemarks());
	            articleVo.setCreateBy(article.getCreateBy());
	            articleVo.setCreateDate(article.getCreateDate());
	            articleVo.setUpdateBy(article.getUpdateBy());
	            articleVo.setUpdateDate(article.getUpdateDate());
	            articleVo.setDelFlag(article.getDelFlag());
	        }
	        return articleVo;
	    }

	 
	//游客根据分享id查看文章信息
	   @Override
	   public ResponseEntity<Article> getArticleByShareId(String shareId){
	       Share share=shareMapper.selectById(shareId);
	       if(share==null){
	           return ResponseEntityUtil.fail("抱歉，没有找到分享数据，或该分享已失效");
	       }
	       Article article=articleMapper.selectTitleById(share.getTypeId());
	       if(article==null){
	           return ResponseEntityUtil.fail("抱歉，没有找到文章，或该文章已失效");
	       }
	       return ResponseEntityUtil.success(article);
	   }

}




	 
