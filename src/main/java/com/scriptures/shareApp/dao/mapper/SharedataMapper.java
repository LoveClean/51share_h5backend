package com.scriptures.shareApp.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.scriptures.shareApp.dao.entity.Sharedata;
import com.scriptures.shareApp.vo.ShareArticleDataVo;

public interface SharedataMapper {
    int deleteByPrimaryKey(String id);

    int insert(Sharedata record);

    int insertSelective(Sharedata record);

    Sharedata selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Sharedata record);

    int updateByPrimaryKey(Sharedata record);
    
    ////////////////////////////////////////////////////
    
    List<Sharedata> selectAll();
    
    Sharedata selectByIpAddress(@Param("ipAddress")String ipAddress);
    
    List<ShareArticleDataVo> selectByTypeId(@Param("typeId")String typeId);


    Integer checkDataByIpAndArticleId(@Param("ip") String ip,@Param("typeId")  String typeId);

    @Select("SELECT count(*) from tb_sharedata where share_id = #{shareId} and remarks = '0'")
    Integer selectShareNumberByShareId(@Param("shareId") String shareId);

    @Select("select count(*) from tb_sharedata where share_id = #{shareId} and remarks = '0' and TIMESTAMPDIFF(MINUTE,create_date,NOW())<#{interval}")
    Integer selectCountByTime(@Param("shareId") String shareId,@Param("interval") int i);
}