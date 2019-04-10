package com.scriptures.shareApp.service;

import com.scriptures.shareApp.util.ResponseEntity;
import javax.servlet.http.HttpServletRequest;

public interface ShareDateService {

    ResponseEntity<Object> articleShareDataRecord(String shareId,Integer shareWay, HttpServletRequest request);

}
