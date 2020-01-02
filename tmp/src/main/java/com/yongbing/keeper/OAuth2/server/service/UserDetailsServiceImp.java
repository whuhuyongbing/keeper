package com.yongbing.keeper.OAuth2.server.service;

import com.yongbing.keeper.OAuth2.server.domain.KpUser;
import com.yongbing.keeper.OAuth2.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @time 2019-12-30 12:22 p.m.
 * @description
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        KpUser kpUser = userMapper.getByEmail(email);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (kpUser != null) {
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
            grantedAuthorities.add(grantedAuthority);
        }
        return new User(kpUser.getEmail(), kpUser.getPassword(), grantedAuthorities);
    }
}
