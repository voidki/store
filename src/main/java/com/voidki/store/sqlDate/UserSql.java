package com.voidki.store.sqlDate;

import com.voidki.store.domain.entity.User;
import com.voidki.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * @projectName: store
 * @package: com.voidki.store.sqlDate
 * @className: UserSql
 * @author: voidki
 * @description:
 * @date: 2023/2/9 16:49
 */
@Component
public class UserSql {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    HashSet<String> set = new HashSet<>();
   public void UserInsert(){
       for (int i = 0; i < 1000; i++) {
           User user = new User();
           String userName =  NameUtils.getStringRandom(5);
           if(set.contains(userName)){
               continue;
           }
           set.add(userName);
           user.setUserName(userName).setPassword(passwordEncoder.encode("1234"));
           userService.save(user);
       }
   }
}
