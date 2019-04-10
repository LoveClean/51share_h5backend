package com.scriptures.shareApp.dao.mapper;


import org.apache.ibatis.annotations.Param;

import com.scriptures.shareApp.dao.entity.Member;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MemberMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
    
    ////////////////////////////////////////////////////////
    //区别于管控端  只能查看状态为 0 3的
    Member selectById(@Param("id")String id);
   
    Integer changePwd(@Param("id")String id,@Param("newPassword")String newPassword);
    
    Member login(@Param("phone")String phone,@Param("password")String password);
    
    int checkPhone(@Param("phone")String phone);
    
    Member selectByPhone(@Param("phone")String phone);

    Member checkNickname(@Param("nickname")String nickname);

    int getAllCounts();

	Integer checkNicknameUpt(@Param("nickname") String nickname,@Param("id") String id);

	@Update("UPDATE tb_member SET  available_balance=available_balance+#{commission} where id=#{id}")
    Integer updateMoney(@Param("commission") Double commission,@Param("id") String memberId);

	@Select("select * from tb_member where openid = #{openId} and del_flag != 1")
    Member selectByOpenId(@Param("openId") String openId);
}