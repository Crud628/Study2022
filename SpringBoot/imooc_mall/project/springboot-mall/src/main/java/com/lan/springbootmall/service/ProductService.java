package com.lan.springbootmall.service;

import com.github.pagehelper.PageInfo;
import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.model.pojo.Product;
import com.lan.springbootmall.model.request.AddProductReq;

/**
 * @author Keason
 * @Description:
 * @date 2022/8/30 20:28
 */
public interface ProductService {
    void add(AddProductReq addProductReq) throws MallException;

    void update(Product updateProduct);

    void delete(Integer id);

    void batchUpdateShellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    Product detail(Integer id);
}
