package com.voidki.store.controller;

import com.voidki.store.domain.ResponseResult;
import com.voidki.store.domain.entity.Product;
import com.voidki.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @projectName: store
 * @package: com.voidki.store.controller
 * @className: ProductContorller
 * @author: voidki
 * @description:
 * @date: 2023/1/31 14:17
 */
@RestController
@RequestMapping("/product")
public class ProductContorller {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResponseResult getAllProducts(Product product,Integer pageNum,Integer pageSize){
            return productService.productsList(product,pageNum,pageSize);
    }
    @PostMapping("/add")
    public ResponseResult register(@RequestBody Product product){
        return productService.register(product);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id){
        productService.removeById(id);
        return ResponseResult.okResult();
    }
    @PutMapping("/update")
    public ResponseResult edit(@RequestBody Product product){
        productService.updateById(product);
        return ResponseResult.okResult();
    }
    @GetMapping(value = { "/{productId}" })
    public ResponseResult getProductInfo(@PathVariable(value = "productId") Long productId){
        Product product = productService.getById(productId);
        return ResponseResult.okResult(product);
    }
}
