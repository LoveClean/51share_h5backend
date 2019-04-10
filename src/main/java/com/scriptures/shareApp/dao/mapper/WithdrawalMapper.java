package com.scriptures.shareApp.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.scriptures.shareApp.dao.entity.Withdrawal;

public interface WithdrawalMapper {
    int deleteByPrimaryKey(String id);

    int insert(Withdrawal record);

    int insertSelective(Withdrawal record);

    Withdrawal selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Withdrawal record);

    int updateByPrimaryKey(Withdrawal record);
    
    /////////////////////////////////////////////////////////
    //根据id查看提现记录：暂时不用
    //@Select("select * from tb_withdrawal where create_by = #{memberId}")
    //List<Withdrawal> selectRecord(@Param("memberId") String memberId);
    @Select("select * from tb_withdrawal where create_by = #{memberPhone} ORDER BY create_date DESC ")
	List<Withdrawal> selectRecordByPhone(@Param("memberPhone") String memberPhone);
}
   