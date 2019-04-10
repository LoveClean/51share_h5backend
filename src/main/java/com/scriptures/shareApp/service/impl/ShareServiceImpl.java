package com.scriptures.shareApp.service.impl;

import com.scriptures.shareApp.dao.entity.Article;
import com.scriptures.shareApp.dao.entity.Commodity;
import com.scriptures.shareApp.dao.mapper.*;
import com.scriptures.shareApp.util.DateUtil;
import org.springframework.stereotype.Service;

import com.scriptures.shareApp.dao.entity.Share;
import com.scriptures.shareApp.service.ShareService;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.util.ResponseEntityUtil;
import com.scriptures.shareApp.util.StringUtil;

import javax.annotation.Resource;

@Service
public class ShareServiceImpl implements ShareService{
	
	@Resource
	private ShareMapper shareMapper;

	@Resource
	private ArticleMapper articleMapper;

	@Resource
	private CommodityMapper  commodityMapper;

	@Resource
	private MemberMapper  memberMapper;

	@Override
	public ResponseEntity<String> shareArticle(String memberId,String memberPhone, String articleId ) {
		//检测该文章是否存在???!!
		Article article=articleMapper.selectByPrimaryKey(articleId);
		if(article==null){
			return ResponseEntityUtil.fail("该文章不存在或已失效");
		}
		//String url="测试域名";
		String shareId="";
		Share share=shareMapper.selectBymemberIdAndTypeId(memberId,articleId);
		if(share!=null){
			shareId=share.getId();
			return ResponseEntityUtil.success(shareId);
		}
		Share shareNew=new Share();
		String id=StringUtil.uuidNotLine();
		//url=url+"?shareId="+id+"&articleId="+articleId;
		shareId=id;
		shareNew.setId(id);
		shareNew.setType(1);
		shareNew.setTypeId(articleId);
		shareNew.setMemberId(memberId);
		shareNew.setCreateBy(memberPhone);
		shareNew.setCreateDate(DateUtil.getCurrentTime());
		shareNew.setShareWay("默认");
		//shareNew.setRemarks(url);
		int resultCount=shareMapper.insertSelective(shareNew);
		if(resultCount==0){
			return ResponseEntityUtil.fail("分享失败");
		}
		return ResponseEntityUtil.success(shareId);
	}

	@Override
	public ResponseEntity<String> shareCommodity(String id, String commodityId) {
		Commodity commodity=commodityMapper.selectByPrimaryKey(commodityId);
		if(commodity==null){
			return ResponseEntityUtil.fail("该商品不存在或已失效");
		}
		String url=commodity.getCommodityLink()+"&memberId="+id;
		return ResponseEntityUtil.success(url);
	}
}
