package com.voidki.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.voidki.store.domain.ResponseResult;
import com.voidki.store.domain.entity.Product;
import com.voidki.store.domain.vo.PageVo;
import com.voidki.store.enums.AppHttpCodeEnum;
import com.voidki.store.exception.SystemException;
import com.voidki.store.mapper.ProductMapper;
import com.voidki.store.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public ResponseResult productsList(Product product,Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(product.getTitle()),Product::getTitle,product.getTitle());
        Page<Product> page = new Page(pageNum,pageSize);
        page(page,queryWrapper);
        List<Product> products = page.getRecords();
        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(products);
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult register(Product product) {
        if(!StringUtils.hasText(product.getTitle())){
            throw new SystemException(AppHttpCodeEnum.TITLE_NOT_NULL);
        }
        save(product);
        return ResponseResult.okResult();
    }
}

