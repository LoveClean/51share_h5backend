package com.scriptures.shareApp.service;

import com.scriptures.shareApp.util.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface ShareCommodityService {

    ResponseEntity<Object> commodityShareDataRecord(String commodityId, String memberId, Integer shareWay, HttpServletRequest request);
}
