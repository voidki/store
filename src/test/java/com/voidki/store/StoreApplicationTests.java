package com.voidki.store;

import com.voidki.store.domain.entity.User;
import com.voidki.store.mapper.UserMapper;
import com.voidki.store.utils.JsonUtil;
import com.voidki.store.utils.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        String carJson ="{ \"userName\" : \"123\", \"password\" : \"123\" }";
        User user = JsonUtil.toBean(carJson, User.class);
        System.out.println(user);
    }
    @Test
    void imageTest(){
        String filePath = PathUtils.generateFilePath();
        System.out.println(filePath);
    }
    @Test
    void imageNameTest(){
        String fileName = "test.jpg";
        String filePath = PathUtils.generateFileName(fileName);
        System.out.println(filePath);
    }
}
