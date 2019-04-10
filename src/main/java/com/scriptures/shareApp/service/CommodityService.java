package com.scriptures.shareApp.service;

import com.scriptures.shareApp.controller.request.CommodityAddRequestBean;
import com.scriptures.shareApp.controller.request.CommodityGetFuzzyRequestBean;
import com.scriptures.shareApp.controller.request.CommodityUptReuquestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Commodity;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.vo.ShareCommodityDataVo;
import com.scriptures.shareApp.vo.ShareDataWithCommodity;
import com.scriptures.shareApp.vo.newShare;

import java.util.List;

public interface CommodityService {




	ResponseEntity<List<Commodity>> getAllByStatus(Member member);

	ResponseEntity<Commodity> getInfo(String id);

	ResponseEntity<Integer> getAllCounts();


	PageResponseBean getCommodityByName(String name, int pageNum, int pageSize);
	
	PageResponseBean<ShareDataWithCommodity> getCommodityGroupByTypeId(String memberId,int pageNum, int pageSize);

}
