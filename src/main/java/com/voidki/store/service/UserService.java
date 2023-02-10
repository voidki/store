package com.voidki.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.voidki.store.domain.ResponseResult;
import com.voidki.store.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-01-31 14:19:55
 */
public interface UserService extends IService<User> {
    ResponseResult register(User user);

    ResponseResult selectUserPage(User user, Integer pageNum, Integer pageSize);
}
