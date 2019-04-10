package com.scriptures.shareApp.service;

import java.util.List;
import com.scriptures.shareApp.controller.request.ShareAddRequestBean;
import com.scriptures.shareApp.controller.request.SharePageSearchRequestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.entity.Share;
import com.scriptures.shareApp.util.ResponseEntity;

public interface ShareService {

	ResponseEntity<String> shareArticle(String id,String memberPhone, String articleId );

	ResponseEntity<String> shareCommodity(String id, String commodityId);
}
