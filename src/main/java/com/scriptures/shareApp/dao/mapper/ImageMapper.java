package com.scriptures.shareApp.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.scriptures.shareApp.dao.entity.Image;

public interface ImageMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
    //////////////////////////////////////////////////
    
    Image selectById(@Param("id")String id);
    
    List<Image> selectAll();
    
    @Select("select * from tb_image where del_flag = 0 ORDER BY create_date DESC limit #{num}")
	List<Image> selectByNum(@Param("num") int num);
    
}