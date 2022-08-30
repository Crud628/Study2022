package com.lan.springbootmall.model.dao;

import com.lan.springbootmall.model.pojo.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * 根据名字查询
     *
     * @param name 传入名字
     * @return 返回对象
     */
    Category selectByName(String name);

    /**
     *
     * @return
     */
    List<Category> selectList();

    /**
     *
     * @param parentID
     * @return
     */
    List<Category> selectCategoriesByParentID(Integer parentID);
}