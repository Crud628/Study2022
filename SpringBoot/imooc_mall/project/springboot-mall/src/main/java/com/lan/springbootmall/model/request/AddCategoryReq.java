package com.lan.springbootmall.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Keason
 * @Description:
 * @date 2022/8/29 21:51
 */
public class AddCategoryReq {
    /**
     * 类型名称
     */
    @Size(min = 2, max = 5)
    @NotNull(message = "name不能为null")
    private String name;

    /**
     * 层级熟
     */
    @NotNull(message = "type不能为null")
    @Max(3)
    private Integer type;

    /**
     * 类型名称
     */
    @NotNull(message = "parentId不能为null")
    private Integer parentId;

    /**
     * 编号
     */
    @NotNull(message = "orderNumber不能为null")
    private Integer orderNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}