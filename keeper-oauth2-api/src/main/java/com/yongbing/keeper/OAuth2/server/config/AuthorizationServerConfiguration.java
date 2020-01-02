package com.yongbing.keeper.OAuth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @time 2019-12-24 3:03 p.m.
 * @description
 */


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    //response_type=token 简化模式
    //grant_type=password 密码模式
    //client_crediential 客户端模式
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("client")
                .secret(passwordEncoder.encode("secret"))
                .resourceIds("resource")
                .authorizedGrantTypes("authorization_code", "password", "implicit", "refresh_token", "client_credentials")
                .autoApprove(true)
                .scopes("All");
    }

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;


    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        services.setSupportRefreshToken(true);
        services.setTokenStore(tokenStore);
        //jwt
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);
        services.setAccessTokenValiditySeconds(15*24*60*60);
        services.setRefreshTokenValiditySeconds(365*24*60*60);
        return services;
    }

    @Autowired
    private AuthenticationManager authenticationManager;
//    @Autowired
//    private ClientDetailsService clientDetailsService;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(new InMemoryAuthorizationCodeServices())
                .tokenServices(tokenServices())//令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    //令牌访问端点安全策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")//oauth/token_key
                .checkTokenAccess("permitAll()")//oauth/check_token
                .allowFormAuthenticationForClients();
    }
}
