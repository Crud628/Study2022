package com.lan.springbootmall.controller;

import com.github.pagehelper.PageInfo;
import com.lan.springbootmall.common.ApiRestResponse;
import com.lan.springbootmall.model.pojo.Product;
import com.lan.springbootmall.model.request.ProductListReq;
import com.lan.springbootmall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Keason
 * @Description:
 * @date 2022/8/31 12:23
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation("商品详情")
    @PostMapping("product/detail")
    public ApiRestResponse detail(@RequestParam Integer id) {
        Product product = productService.detail(id);
        return ApiRestResponse.success(product);
    }

    @ApiOperation("商品详情")
    @PostMapping("product/list")
    public ApiRestResponse List(@RequestParam ProductListReq productListReq) {
        PageInfo list = productService.list(productListReq);
        return ApiRestResponse.success(list);
    }
}