package com.scriptures.shareApp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.mapper.ShareMapper;
import com.scriptures.shareApp.vo.CommodityShareVo;
import com.scriptures.shareApp.vo.ShareCommodityDataVo;
import com.scriptures.shareApp.vo.ShareDataWithCommodity;
import com.scriptures.shareApp.vo.newShare;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scriptures.shareApp.controller.request.CommodityAddRequestBean;
import com.scriptures.shareApp.controller.request.CommodityGetFuzzyRequestBean;
import com.scriptures.shareApp.controller.request.CommodityUptReuquestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Article;
import com.scriptures.shareApp.dao.entity.Commodity;
import com.scriptures.shareApp.dao.entity.Commoditycategories;
import com.scriptures.shareApp.dao.mapper.CommodityMapper;
import com.scriptures.shareApp.dao.mapper.CommoditycategoriesMapper;
import com.scriptures.shareApp.service.CommodityService;
import com.scriptures.shareApp.util.DateUtil;
import com.scriptures.shareApp.util.ListUtil;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.util.ResponseEntityUtil;
import com.scriptures.shareApp.util.StringUtil;

/**
 * BUG   两个分页 
 *       查看商品详细信息  没有加删除 没有转换品类
 * @author hp
 *
 */
@Service
public class CommodityServiceImpl implements CommodityService {

	@Resource
	private CommodityMapper commodityMapper;

	@Resource
	private ShareMapper shareMapper;
	
	@Resource
	private CommoditycategoriesMapper categoryMapper;




	@Override
	public ResponseEntity<List<Commodity>> getAllByStatus(Member member) {
		List<Commodity> commodities = commodityMapper.selectAllByStatus();
		List<Commodity> commodities2=new ArrayList<>();
		if (commodities != null && commodities.size() > 0) {
			if(member==null){
				System.out.println("会员未登录");
				for (Commodity commodity : commodities) {
					if(StringUtil.isEmpty(commodity.getLabel())){
						commodities2.add(commodity);
					}
				}
			}else{
				int memberCreateDay=DateUtil.daysBetween(member.getCreateDate(),new Date());
				System.out.println(memberCreateDay);
				for (Commodity commodity : commodities) {
					String cL=commodity.getLabel();
					if(StringUtil.isEmpty(cL)){
						commodities2.add(commodity);
					}else {
						//截取动态标签
						int b=cL.indexOf("#");
						int a=cL.lastIndexOf("#");
						if(b!=-1&&a!=-1){
							int day=Integer.parseInt(cL.substring(b+1,a));
							System.out.println(day);
							if(memberCreateDay<day){
								commodities2.add(commodity);
								continue;
							}
						}
						//判断是否有交集
						if (ListUtil.checkLabel(member.getLabel(), cL)) {
							commodities2.add(commodity);
						}
					}
				}
			}

			return ResponseEntityUtil.success(commodities2);
		}
		return ResponseEntityUtil.fail("没有可见商品");
	}



	@Override
	public ResponseEntity<Commodity> getInfo(String id) {
		Commodity commodity=commodityMapper.selectByPrimaryKey(id);
		if(commodity==null) {
			return ResponseEntityUtil.fail("没有查询到相关商品");
		}	
		return ResponseEntityUtil.success(commodity);
	}

	


	@Override
	public ResponseEntity<Integer> getAllCounts() {
		int counts=commodityMapper.getAllCounts();
		return ResponseEntityUtil.success(counts);
	}

	@Override
	public PageResponseBean getCommodityByName(String name,int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Commodity> commodities=commodityMapper.selectByName(name);
		List<CommodityShareVo> commodityShareVos=new ArrayList<>();
		for (Commodity commodity:commodities) {
			String id=commodity.getId();
			CommodityShareVo commodityShareVo=new CommodityShareVo();
			commodityShareVo.setId(id);
			commodityShareVo.setCommodityName(commodity.getCommodityName());
			commodityShareVos.add(commodityShareVo);
		}

		PageInfo pageInfo = new PageInfo<>(commodities);
		pageInfo.setList(commodityShareVos);
		PageResponseBean<CommodityShareVo> page = new PageResponseBean<CommodityShareVo>(pageInfo);
		page.setCode(0);
		page.setHttpStatus(200);
		return page;

	}
	@Override
	public PageResponseBean<ShareDataWithCommodity> getCommodityGroupByTypeId(String memberId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ShareDataWithCommodity> newShares=shareMapper.selectGroupByTypeIdOfCommodity2(memberId);
		PageInfo<ShareDataWithCommodity> pageInfo = new PageInfo<>(newShares);
		PageResponseBean<ShareDataWithCommodity> pageResponseBean = new PageResponseBean<>(pageInfo);
		pageResponseBean.setCode(0);
		pageResponseBean.setHttpStatus(200);
		return pageResponseBean;
	}


}
