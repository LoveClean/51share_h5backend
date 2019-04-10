package com.scriptures.shareApp.dao.mapper;

import org.apache.ibatis.annotations.Select;

public interface SwitchMapper {

    @Select("select switch from tb_switch where id = 1")
    int selectSwitch();
}
