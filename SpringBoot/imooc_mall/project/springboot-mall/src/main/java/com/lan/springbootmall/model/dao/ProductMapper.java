package com.lan.springbootmall.model.dao;

import com.lan.springbootmall.model.pojo.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    /**
     *
     * @param Name
     * @return
     */
    Product selectByName(String Name);
}