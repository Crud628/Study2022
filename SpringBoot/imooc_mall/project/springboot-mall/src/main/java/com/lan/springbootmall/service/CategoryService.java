package com.lan.springbootmall.service;

import com.github.pagehelper.PageInfo;
import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.model.pojo.Category;
import com.lan.springbootmall.model.request.AddCategoryReq;
import com.lan.springbootmall.model.vo.CategoryVO;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

/**
 * @author Keason
 * @Description: 分类目录
 * @date 2022/8/29 22:19
 */
public interface CategoryService {
    void add(AddCategoryReq addCategoryReq) throws MallException;

    void delete(Integer id) throws MallException;

    @Cacheable(value = "listCategoryForCustomer")
    List<CategoryVO> listCategoryForCustomer(Integer parentId);

    PageInfo ListForAdmin(Integer pageNum, Integer pageSize);

    List<CategoryVO> getCategoryForCustomerList();

    void update(Category updateCategory) throws MallException;
}
