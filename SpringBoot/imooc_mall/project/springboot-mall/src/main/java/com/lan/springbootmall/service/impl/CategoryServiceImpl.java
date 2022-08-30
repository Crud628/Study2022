package com.lan.springbootmall.service.impl;

import com.lan.springbootmall.common.ApiRestResponse;
import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.exception.MallExceptionEnum;
import com.lan.springbootmall.model.dao.CategoryMapper;
import com.lan.springbootmall.model.pojo.Category;
import com.lan.springbootmall.model.request.AddCategoryReq;
import com.lan.springbootmall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 
 * @author Keason
 * @Description: 目录分类实现类
 * @date 2022/8/29 23:02
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void add(AddCategoryReq addCategoryReq) throws MallException {
        Category category = new Category();
        // 相同字段复制
        BeanUtils.copyProperties(addCategoryReq, category);
        Category categoryOld = categoryMapper.selectByName(addCategoryReq.getName());
        if (!ObjectUtils.isEmpty(categoryOld)) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }
        int count = categoryMapper.insertSelective(category);
        if (count == 0 ) {
            throw new MallException(MallExceptionEnum.CREATE_FAILED);
        }

    }
}