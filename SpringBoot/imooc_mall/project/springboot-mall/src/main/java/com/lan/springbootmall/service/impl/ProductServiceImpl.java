package com.lan.springbootmall.service.impl;

import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.exception.MallExceptionEnum;
import com.lan.springbootmall.model.dao.ProductMapper;
import com.lan.springbootmall.model.pojo.Product;
import com.lan.springbootmall.model.request.AddProductReq;
import com.lan.springbootmall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 
 * @author Keason
 * @Description:
 * @date 2022/8/30 20:28
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public void add(AddProductReq addProductReq) throws MallException {
        Product product = new Product();
        BeanUtils.copyProperties(addProductReq, product);
        Product productOld = productMapper.selectByName(addProductReq.getName());
        if (ObjectUtils.isEmpty(product)) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }
        int count = productMapper.insert(product);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.CREATE_FAILED);
        }
    }

    @Override
    public void update(Product updateProduct) {
        Product productOld = productMapper.selectByName(updateProduct.getName());
        // 同名且不同ID，不能继续修改
        if (productOld != null && productOld.getId().equals(updateProduct.getId())) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }
    }
}