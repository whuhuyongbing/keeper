package com.yongbing.keeper.OAuth2.server.service;

import com.yongbing.keeper.OAuth2.server.common.KpResult;
import com.yongbing.keeper.OAuth2.server.domain.KpUser;

public interface UserService {
    KpResult login(KpUser kpUser);
    KpResult register(KpUser kpUser);
}
