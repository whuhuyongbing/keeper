package com.yongbing.keeper.service;

import com.yongbing.keeper.domain.KpUser;

public interface UserService {


    KpUser selectByEmail(String email);
}
