package com.scriptures.shareApp.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.entity.Syslog;
import com.scriptures.shareApp.dao.entity.Withdrawal;
import com.scriptures.shareApp.dao.mapper.SyslogMapper;
import com.scriptures.shareApp.service.SyslogService;
import com.scriptures.shareApp.util.StringUtil;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SyslogServiceImpl implements SyslogService {

    @Resource
    private SyslogMapper syslogMapper;

    @Override
    public int add(Syslog sysLog, Object object) {
        Member member = null;
       

        if (object.getClass() == Member.class) {
            member = (Member) object;
        }
       
        if (member != null) {
            sysLog.setId(StringUtil.uuidNotLine());
            sysLog.setCreateBy(member.getNickname());
            sysLog.setCreateDate(new Date());
            syslogMapper.insertSelective(sysLog);
        }
     

        return 0;
    }

    @Override
    public PageResponseBean<Syslog> pageSysLogAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Syslog> syslogs = (List<Syslog>) syslogMapper.selectAll();
        PageInfo<Syslog> pageInfo = new PageInfo<>();
        pageInfo.setList(syslogs);
        int nums = syslogMapper.selectAll().size();
        int totalPage = nums % pageSize == 0 ? nums / pageSize : nums / pageSize + 1;
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(nums);
        pageInfo.setPages(totalPage);
        PageResponseBean<Syslog> page = new PageResponseBean<Syslog>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(1);
        return page;
    }


}
