package com.scriptures.shareApp.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.scriptures.shareApp.dao.entity.*;
import com.scriptures.shareApp.dao.mapper.*;
import com.scriptures.shareApp.util.*;
import org.springframework.stereotype.Service;

import com.scriptures.shareApp.service.ShareDateService;

@Service
public class ShareDateServiceImpl implements ShareDateService{

	@Resource
	private ShareMapper shareMapper;

	@Resource
	private SharedataMapper sharedataMapper;

	@Resource
	private MemberMapper memberMapper;

	@Resource
	private ArticleMapper articleMapper;

	@Resource
	private ConfigMapper configMapper;
	//记录访问信息，分享信息
	@Override
	public ResponseEntity<Object> articleShareDataRecord(String shareId,
														 Integer shareWay,
														 HttpServletRequest request) {
		HttpUtil httpUtil=new HttpUtil();
		String ip=httpUtil.getClientIp(request);
		Share share=shareMapper.selectByPrimaryKey(shareId);

		if(share==null){
			return ResponseEntityUtil.fail("该分享不存在或已失效");
		}
		//检测会员状态   如果会员被禁用或者删除  则分享无效
		Member member = memberMapper.selectById(share.getMemberId());
		if(member==null){
			return ResponseEntityUtil.fail("该会员不存在或者已被禁用");
		}
		//获取分享配置信息
		Config config = configMapper.selectByPrimaryKey(1);
		//System.err.println(config.getIntervalTime()+","+config.getMaxShare());
		//检测有效分享次数
		Integer shareNumber = sharedataMapper.selectShareNumberByShareId(shareId);
		if(shareNumber>=config.getMaxShare()){
		   return ResponseEntityUtil.fail("有效分享次数已达上限");
		}
		Sharedata sharedata=new Sharedata();
		sharedata.setId(StringUtil.uuidNotLine());
		sharedata.setShareId(shareId);
		sharedata.setType(1);
		sharedata.setIpAddress(ip);
		sharedata.setCreateDate(DateUtil.getCurrentTime());
		sharedata.setShareType(shareWay);
		//检测分享浏览有效性
		//1.检测该ip是否浏览过此文章
		int checkData=sharedataMapper.checkDataByIpAndArticleId(ip,share.getTypeId());
		if(checkData>0){
			//该ip已经浏览过此文章
			sharedata.setRemarks("1");
			int resultCount=sharedataMapper.insertSelective(sharedata);
			return ResponseEntityUtil.addMessage(resultCount);
		}else {
			//该ip没有浏览过此文章
			//检测此文章有效浏览时间间隔  预设两分钟  后期可修改
			int interval = sharedataMapper.selectCountByTime(shareId,config.getIntervalTime());
			if(interval>0){
				return ResponseEntityUtil.fail("您点的太快了，请稍后重试");
			}
			sharedata.setRemarks("0");
			//插入分享数据表
			int resultCount=sharedataMapper.insertSelective(sharedata);
			if(resultCount==0){
				return ResponseEntityUtil.fail("浏览记录添加失败");
			}
			Article article=articleMapper.selectByPrimaryKey(share.getTypeId());
			//分享表更新
			int resultCount2=shareMapper.updateMoney(article.getCommission(),share.getId());
			if (resultCount2==0){
				return ResponseEntityUtil.fail("分享信息更新失败");
			}
			//余额更新
			int resultCount3=memberMapper.updateMoney(article.getCommission(),share.getMemberId());
			if (resultCount3==0){
				return ResponseEntityUtil.fail("用户信息更新失败");
			}
		}
		return ResponseEntityUtil.success();
	}
}
