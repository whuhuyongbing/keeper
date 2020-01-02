package com.yongbing.keeper.OAuth2.server.mapper;

import com.yongbing.keeper.OAuth2.server.domain.KpUser;
import org.apache.ibatis.annotations.*;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @time 2019-12-29 7:54 p.m.
 * @description
 */

@Mapper
public interface UserMapper {

    @Select("select * from user where email = #{email}")
    KpUser getByEmail(@Param("email") String email);

    @Insert("insert into user(id, username, password, email) values(#{id}, #{username}, #{password}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(KpUser kpUser);
}
