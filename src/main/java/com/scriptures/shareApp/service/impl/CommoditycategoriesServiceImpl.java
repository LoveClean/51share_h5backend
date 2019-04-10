package com.scriptures.shareApp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scriptures.shareApp.controller.request.CommoditycategoriesAddRequestBean;
import com.scriptures.shareApp.controller.request.CommoditycategoriesUptRequestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Article;
import com.scriptures.shareApp.dao.entity.Commodity;
import com.scriptures.shareApp.dao.entity.Commoditycategories;
import com.scriptures.shareApp.dao.mapper.CommodityMapper;
import com.scriptures.shareApp.dao.mapper.CommoditycategoriesMapper;
import com.scriptures.shareApp.service.CommoditycategoriesService;
import com.scriptures.shareApp.util.DateUtil;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.util.ResponseEntityUtil;
import com.scriptures.shareApp.util.StringUtil;
import com.scriptures.shareApp.vo.ArticleVo;
import com.scriptures.shareApp.vo.CommoditycategoriesVo;


@Service
public class CommoditycategoriesServiceImpl implements CommoditycategoriesService {

	@Resource
	private CommoditycategoriesMapper categorysMapper;
	@Resource
	private CommodityMapper commodityMapper;



	// 列表分页查询
	@Override
	public PageResponseBean<Commoditycategories> getAll(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Commoditycategories> categorys = categorysMapper.selectAll();
		// List<CommoditycategoriesVo> commoditycategoriesVos=new ArrayList<>();
		// if(categorys!=null && categorys.size()>0) {
		// for(Commoditycategories category :categorys) {
		// commoditycategoriesVos.add(createCategoryVo(category));
		// }
		// }
		PageInfo pageInfo = new PageInfo<>(categorys);
		// pageInfo.setList(commoditycategoriesVos);
		PageResponseBean<Commoditycategories> pageResponseBean = new PageResponseBean<>(pageInfo);
		pageResponseBean.setCode(0);
		pageResponseBean.setHttpStatus(200);
		return pageResponseBean;
	}

	// 根据id具体查询
	@Override
	public ResponseEntity<Object> getInfo(String id) {
		Commoditycategories commoditycategories = categorysMapper.selectByPrimaryKey(id);
		if (commoditycategories == null) {
			return ResponseEntityUtil.fail("查不到数据");
		}
		// CommoditycategoriesVo categoryVo=createCategoryVo(commoditycategories);
		return ResponseEntityUtil.success(commoditycategories);
	}

	public CommoditycategoriesVo createCategoryVo(Commoditycategories commoditycategories) {
		CommoditycategoriesVo commoditycategoriesVo = new CommoditycategoriesVo();

		if (commoditycategories != null) {
			commoditycategoriesVo.setId(commoditycategories.getId());
			commoditycategoriesVo.setCategoriesname(commoditycategories.getCategoriesname());
			commoditycategoriesVo.setOrderby(commoditycategories.getOrderby());
			commoditycategoriesVo.setCreateBy(commoditycategories.getCreateBy());
		}

		return commoditycategoriesVo;
	}



}
