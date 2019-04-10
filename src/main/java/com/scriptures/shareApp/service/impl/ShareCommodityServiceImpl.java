package com.scriptures.shareApp.service.impl;

import com.scriptures.shareApp.dao.entity.Commodity;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.entity.ShareCommodity;
import com.scriptures.shareApp.dao.mapper.CommodityMapper;
import com.scriptures.shareApp.dao.mapper.MemberMapper;
import com.scriptures.shareApp.dao.mapper.ShareCommodityMapper;
import com.scriptures.shareApp.service.ShareCommodityService;
import com.scriptures.shareApp.util.HttpUtil;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.util.ResponseEntityUtil;




import com.scriptures.shareApp.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class ShareCommodityServiceImpl implements ShareCommodityService {
    @Resource
    private ShareCommodityMapper shareCommodityMapper;

    @Resource
    private CommodityMapper commodityMapper;
    
    @Resource
    private MemberMapper memberMapper;

    @Override
    public ResponseEntity<Object> commodityShareDataRecord(String commodityId, String memberId, Integer shareWay, HttpServletRequest request) {
    	Commodity commodity=commodityMapper.selectByPrimaryKey(commodityId);
    	if(commodity==null){
    	    return ResponseEntityUtil.fail("该商品已经失效");
    	}
    	Member member= memberMapper.selectById(memberId);
    	if(member!=null){
    	    HttpUtil httpUtil=new HttpUtil();
    	    String ip=httpUtil.getClientIp(request);
    	    ShareCommodity shareCommodity=new ShareCommodity();
    	    shareCommodity.setId(StringUtil.uuidNotLine());
    	    shareCommodity.setCommodityId(commodityId);
    	    shareCommodity.setMemberId(memberId);
    	    shareCommodity.setShareType(shareWay);
    	    shareCommodity.setIpAddress(ip);
    	    shareCommodity.setMemberPhone(member.getPhone());
    	    Integer result=shareCommodityMapper.insertSelective(shareCommodity);
    	    return ResponseEntityUtil.addMessage(result);
    	}
    	return ResponseEntityUtil.success();
    }
}
