package com.voidki.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.voidki.store.domain.entity.LoginUser;
import com.voidki.store.domain.entity.User;
import com.voidki.store.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @projectName: store
 * @package: com.voidki.store.service.impl
 * @className: UserDetailsServiceImpl
 * @author: voidki
 * @description:
 * @date: 2023/2/1 17:37
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 数据库查找用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,s);
        wrapper.eq(User::getType,"1");
        User user = userMapper.selectOne(wrapper);
        //判断是否查到用户  如果没查到抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        //返回用户信息
        return new LoginUser(user);
    }
}
