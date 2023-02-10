package com.voidki.store;

import com.voidki.store.domain.entity.User;
import com.voidki.store.mapper.ProductMapper;
import com.voidki.store.mapper.UserMapper;
import com.voidki.store.service.ProductService;
import com.voidki.store.sqlDate.UserSql;
import com.voidki.store.utils.JsonUtil;
import com.voidki.store.utils.PathUtils;
import com.voidki.store.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserSql userSql;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
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
    @Test
    void userSqlTest(){
        productMapper.deleteById(2L);
    }
    @Test
    void passwordTest(){
        System.out.println(SecurityUtils.getLoginUser().getPassword());
    }
}
