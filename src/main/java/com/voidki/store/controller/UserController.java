package com.voidki.store.controller;

import com.voidki.store.domain.ResponseResult;
import com.voidki.store.domain.entity.User;
import com.voidki.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/register")
    public ResponseResult addUser(@RequestBody User user){
        return userService.register(user);
    }

}
