package com.scriptures.shareApp.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.scriptures.shareApp.dao.entity.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    @Select("select * from tb_article where del_flag = 0")
    List<Article> selectAll();
    
    @Select("select * from tb_article where del_flag = 0 and remarks = 0 ORDER BY create_date DESC")
    List<Article> selectAllByStatus();
    
    List<Article> selectFuzzy(@Param("author") String author, @Param("remarks") String remarks, @Param("articleTitle") String articleTitle, @Param("createBy") String createBy);
 
    List<Article> selectFuzzyByKey(@Param("key") String key);

    int getAllCounts();

    List<Article> selectByTitle(@Param("title") String title);
    
    Article selectTitleById(@Param("id")String id);

    @Select("SELECT * FROM tb_article WHERE article_title=#{articleTitle} AND del_flag=0")
    Article selectByArticleTitle(@Param("articleTitle") String articleTitle);
    
    
}