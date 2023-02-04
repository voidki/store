package com.voidki.store.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.voidki.store.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    Long userId;
    private void setUserId(){
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            userId = -1L;//表示是自己创建
        }
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        setUserId();
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy",userId , metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setUserId();
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }
}