package com.voidki.store.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.voidki.store.domain.ResponseResult;
import com.voidki.store.domain.entity.Product;
import com.voidki.store.mapper.ProductMapper;
import com.voidki.store.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Product)表服务实现类
 *
 * @author makejava
 * @since 2023-01-31 14:13:21
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Override
    public ResponseResult productsList(Integer pageNum, Integer pageSize) {
        Page<Product> page = new Page(pageNum,pageSize);
        List<Product> products = page.getRecords();
        return ResponseResult.okResult(products);
    }
}

