package com.hth.service;

import com.hth.domain.ResponseResult;
import com.hth.domain.entity.User;

public interface AdminLoginService {
     ResponseResult login(User user);

    ResponseResult logout();
}
