package com.lan.springbootmall.model.query;

import java.util.List;

/**
 * 
 * @author Keason
 * @Description: 查询商品列表的Query
 * @date 2022/8/31 13:08
 */
public class ProductListQuery {
    private String keyword;

    private List<Integer> categoryIds;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }
}