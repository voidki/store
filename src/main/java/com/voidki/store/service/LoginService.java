package com.voidki.store.service;

import com.voidki.store.domain.ResponseResult;
import com.voidki.store.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);
    ResponseResult logout();
}
