package com.scriptures.shareApp.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scriptures.shareApp.dao.entity.Article;
import com.scriptures.shareApp.dao.entity.Commodity;

public interface CommodityMapper {
    int deleteByPrimaryKey(String id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);
    
    ///////////////////////////////////////////////
    
    @Select("select * from tb_commodity where del_flag = 0")
	List<Commodity> selectAll();
   
    @Select("select * from tb_commodity where del_flag = 0 and remarks = 0 ORDER BY create_date DESC")
    List<Commodity> selectAllByStatus();
    
	List<Commodity> selectByCategory(String id);

    int getAllCounts();

    List<Commodity> selectByName(@Param("name") String name);

    @Select("SELECT * FROM tb_commodity WHERE commodity_name=#{categoriesName} AND del_flag=0")
    Commodity selectByCategoriesName(@Param("categoriesName") String categoriesName);
	
}