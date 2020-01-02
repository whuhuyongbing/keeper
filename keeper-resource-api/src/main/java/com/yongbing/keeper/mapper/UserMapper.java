package com.yongbing.keeper.mapper;

import com.yongbing.keeper.domain.KpUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @description
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(id, username, password, email) values(#{id}, #{username}, #{password}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(KpUser kpUser);

    @Select("select * from user where email = #{email}")
    KpUser selectByEmail(String email);


}
