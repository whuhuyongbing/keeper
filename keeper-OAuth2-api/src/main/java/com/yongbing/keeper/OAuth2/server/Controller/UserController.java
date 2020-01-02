package com.yongbing.keeper.OAuth2.server.Controller;

import com.yongbing.keeper.OAuth2.server.common.KpResult;
import com.yongbing.keeper.OAuth2.server.domain.KpUser;
import com.yongbing.keeper.OAuth2.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @time 2019-12-29 4:15 p.m.
 * @description
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public KpResult register(KpUser kpUser) {
        return userService.register(kpUser);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public KpResult login(KpUser kpUser) {
        return userService.login(kpUser);
    }

}
