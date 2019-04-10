package com.scriptures.shareApp.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scriptures.shareApp.dao.entity.Article;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.dao.entity.Share;
import com.scriptures.shareApp.vo.ShareDataWithCommodity;
import com.scriptures.shareApp.vo.newShare;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ShareMapper {
    int deleteByPrimaryKey(String id);

    int insert(Share record);

    int insertSelective(Share record);

    Share selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Share record);

    int updateByPrimaryKey(Share record);
    
    ////////////////////////////////////////////////////
    
    Share selectById(@Param("id")String id);
    
    List<Share> selectAll();

    List<Share> selectByTypeId(@Param("typeId") String typeId);
    
    List<Share> selectBymemberId(@Param("memberId") String memberId);

    List<newShare> selectGroupByTypeIdOfArticle(@Param("memberId") String memberId);
    
    List<ShareDataWithCommodity> selectGroupByTypeIdOfCommodity(@Param("memberId") String memberId);

    List<ShareDataWithCommodity> selectGroupByTypeIdOfCommodity2(@Param("memberId") String memberId);

    @Update("UPDATE tb_share SET clicks=clicks+1 , reward=reward+#{commission} where id=#{id}")
    Integer updateMoney(@Param("commission") Double commission,@Param("id") String id);

    @Select("select * from tb_share where member_id=#{memberId} and type_id=#{articleId}")
    Share selectBymemberIdAndTypeId(@Param("memberId") String memberId,@Param("articleId") String articleId);
}