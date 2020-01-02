package com.yongbing.keeper.mapper;

import com.yongbing.keeper.domain.KpContent;
import com.yongbing.keeper.domain.KpUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ContentMapper {

    @Insert("insert into content(title, content, userID) values(#{title}, #{content}, #{userID})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(KpContent kpContent);

    @Select("select * from content where userID=#{userID} and date like #{date} ")
    List<KpContent> selectByDate(@Param("userID") Long userID, @Param("date") String date);

    @Select("select * from user where email=#{email}")
    KpUser selectByEmail(@Param("email") String email);

    @Delete("delete from content where userID=#{userID} and (title=#{title} or title is null) and content=#{content}")
    void deleteContent(KpContent kpContent);
}
