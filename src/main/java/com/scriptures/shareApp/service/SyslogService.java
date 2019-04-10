package com.scriptures.shareApp.service;

import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.entity.Syslog;

public interface SyslogService {

	public int add(Syslog sysLog,Object object);
	
	public PageResponseBean<Syslog> pageSysLogAll(Integer pageNum,Integer pageSize);

	
}
