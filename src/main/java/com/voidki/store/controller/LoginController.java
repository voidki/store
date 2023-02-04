package com.voidki.store.controller;

import com.voidki.store.domain.ResponseResult;
import com.voidki.store.domain.entity.User;
import com.voidki.store.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: store
 * @package: com.voidki.store.controller
 * @className: LoginController
 * @author: voidki
 * @description:
 * @date: 2023/2/1 15:13
 */
@RestController
public class LoginController {
   // 调用service接口返回 token
   @Autowired
   private LoginService loginService;

   @PostMapping("/login")
   public ResponseResult login(@RequestBody User user){
       return loginService.login(user);
   }
   @PostMapping("/logout")
   public ResponseResult logout(){
      return loginService.logout();
   }
}
