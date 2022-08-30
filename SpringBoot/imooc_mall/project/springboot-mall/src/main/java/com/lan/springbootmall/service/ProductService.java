package com.lan.springbootmall.service;

import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.model.request.AddProductReq;

/**
 * @author Keason
 * @Description:
 * @date 2022/8/30 20:28
 */
public interface ProductService {
    void add(AddProductReq addProductReq) throws MallException;
}
