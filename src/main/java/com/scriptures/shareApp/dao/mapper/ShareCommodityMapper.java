package com.scriptures.shareApp.dao.mapper;

import com.scriptures.shareApp.dao.entity.ShareCommodity;

public interface ShareCommodityMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShareCommodity record);

    int insertSelective(ShareCommodity record);

    ShareCommodity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShareCommodity record);

    int updateByPrimaryKey(ShareCommodity record);
}