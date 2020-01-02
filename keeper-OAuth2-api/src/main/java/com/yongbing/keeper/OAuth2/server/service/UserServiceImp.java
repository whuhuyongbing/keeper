package com.yongbing.keeper.OAuth2.server.service;

import com.yongbing.keeper.OAuth2.server.common.KpResult;
import com.yongbing.keeper.OAuth2.server.domain.KpUser;
import com.yongbing.keeper.OAuth2.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @time 2019-12-29 2:46 p.m.
 * @description
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public KpResult login(KpUser kpUser) {
        KpUser user = userMapper.getByEmail(kpUser.getEmail());
        if (user != null) {
            if (passwordEncoder.matches(kpUser.getPassword(), user.getPassword()))
                return KpResult.createKpResult(200, KpResult.NO_ERROR, "success");
            else
                return KpResult.createKpResult(201, KpResult.PASSWORD_ERROR, "password is not correct");
        } else
            return KpResult.createKpResult(201, KpResult.USER_NOT_EXIST, "User not exsit");

    }

    @Override
    public KpResult register(KpUser kpUser) {
        KpUser user = userMapper.getByEmail(kpUser.getEmail());
        if (user != null) {
            return KpResult.createKpResult(201, KpResult.USER_ALREADY_EXIST, "The email already registered");
        } else {
            kpUser.setPassword(passwordEncoder.encode(kpUser.getPassword()));
            userMapper.insert(kpUser);
            return KpResult.createKpResult(200, KpResult.NO_ERROR, "success");
        }
    }
}
