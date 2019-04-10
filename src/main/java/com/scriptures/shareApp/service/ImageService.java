package com.scriptures.shareApp.service;

import java.util.List;

import com.scriptures.shareApp.controller.request.ImageAddRequestBean;
import com.scriptures.shareApp.controller.request.ImageUpdateRequestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Image;
import com.scriptures.shareApp.util.ResponseEntity;

public interface ImageService {

	ResponseEntity<List<Image>> getImage();

	ResponseEntity<Object> selectInfo(String imagename, Integer type);
}
