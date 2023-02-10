package com.voidki.store.controller;

import com.voidki.store.domain.ResponseResult;
import com.voidki.store.domain.entity.User;
import com.voidki.store.service.LoginService;
import com.voidki.store.service.UserService;
import com.voidki.store.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * @projectName: store
 * @package: com.voidki.store.controller
 * @className: UserController
 * @author: voidki
 * @description:
 * @date: 2023/2/1 18:16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoginService loginService;
    @PostMapping("/register")
    public ResponseResult addUser(@RequestBody User user){
        return userService.register(user);
    }
    @GetMapping("/list")
    public ResponseResult list(User user,Integer pageNum,Integer pageSize){
        return userService.selectUserPage(user,pageNum,pageSize);
    }
    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody Map<String,String> map){
        User user = new User();
        Long userId = Long.valueOf(map.get("userId"));
        user.setId(userId).setStatus(map.get("status"));
        return ResponseResult.okResult(userService.updateById(user));
    }
    @GetMapping(value = { "/{userId}" })
    public ResponseResult getUserInfo(@PathVariable(value = "userId") Long userId){
        User user = userService.getById(userId);
        return ResponseResult.okResult(user);
    }
    @PostMapping("/verify")
    public ResponseResult verifyPassword(@RequestBody User user){
        String password = user.getPassword();
        String result = SecurityUtils.getLoginUser().getPassword();
        return ResponseResult.okResult(passwordEncoder.matches(password,result));
    }
    @PutMapping("/resetPassword")
    public ResponseResult resetPassword(@RequestBody User user){
        User newUser = new User();
        newUser.setId(SecurityUtils.getUserId()).setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateById(newUser);
        loginService.logout();
        return ResponseResult.okResult();
    }

}
