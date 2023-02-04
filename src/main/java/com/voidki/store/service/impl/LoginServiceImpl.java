package com.voidki.store.service.impl;

import com.voidki.store.domain.ResponseResult;
import com.voidki.store.domain.entity.LoginUser;
import com.voidki.store.domain.entity.User;
import com.voidki.store.domain.vo.UserInfo;
import com.voidki.store.domain.vo.UserLoginVo;
import com.voidki.store.service.LoginService;
import com.voidki.store.utils.BeanCopyUtils;
import com.voidki.store.utils.JwtUtil;
import com.voidki.store.utils.MyCache;
import com.voidki.store.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @projectName: store
 * @package: com.voidki.store.service.impl
 * @className: LoginServiceImpl
 * @author: voidki
 * @description:
 * @date: 2023/2/1 15:18
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {
        //封装账号密码
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        // 判断认证
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //成功则jwt生成token
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("账号或密码错误");
        }
        LoginUser userDetail = (LoginUser) authenticate.getPrincipal();
        String userId = userDetail.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息加入缓存
        MyCache.put("sys_"+userId,userDetail);
        //获得user信息封装返回
        UserInfo userInfo = BeanCopyUtils.copyBean(userDetail.getUser(), UserInfo.class);
        return ResponseResult.okResult(new UserLoginVo(jwt,userInfo));
    }

    @Override
    public ResponseResult logout() {
        Long userId = SecurityUtils.getUserId();
        MyCache.remove("sys_"+userId);
        return ResponseResult.okResult();
    }
}
