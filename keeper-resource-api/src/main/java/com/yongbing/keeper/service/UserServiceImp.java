package com.yongbing.keeper.service;

import com.yongbing.keeper.domain.KpUser;
import com.yongbing.keeper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @description
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserMapper userMapper;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public KpUser selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }
}
