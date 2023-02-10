package com.voidki.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.voidki.store.domain.ResponseResult;
import com.voidki.store.domain.entity.Product;


/**
 * (Product)表服务接口
 *
 * @author makejava
 * @since 2023-01-31 14:13:21
 */
public interface ProductService extends IService<Product> {
    ResponseResult productsList(Product product,Integer pageNum,Integer pageSize);
    ResponseResult register(Product product);
}
