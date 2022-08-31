package com.lan.springbootmall.model.dao;

import com.lan.springbootmall.model.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     *
     * @param ids
     * @param sellStatus
     * @return
     */
    int batchUpdateShellStatus(@Param("ids") Integer[] ids, @Param("sellStatus") Integer sellStatus);

    /**
     *
     * @return
     */
    List<Product> selectLsitForAdmin();
}