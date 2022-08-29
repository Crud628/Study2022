package com.lan.springbootmall.service;

import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.model.request.AddCategoryReq;

/**
 * @author Keason
 * @Description: 分类目录
 * @date 2022/8/29 22:19
 */
public interface CategoryService {
    void add(AddCategoryReq addCategoryReq) throws MallException;
}
