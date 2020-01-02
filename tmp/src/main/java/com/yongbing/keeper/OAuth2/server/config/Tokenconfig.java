package com.yongbing.keeper.OAuth2.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @time 2019-12-26 10:49 p.m.
 * @description
 */

@Configuration
public class Tokenconfig {

    private static final String SIGNING_KEY = "keeper";

    //how to save token
    /**@Bean
    public TokenStore tokenStore() {
        // in memory
        return new InMemoryTokenStore();
    }
    */

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }


    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }


}
