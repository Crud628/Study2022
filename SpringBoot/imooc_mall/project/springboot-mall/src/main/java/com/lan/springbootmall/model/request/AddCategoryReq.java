package com.lan.springbootmall.model.request;
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
    private String namne;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 类型名称
     */
    private Integer parentId;

    /**
     * 编号
     */
    private Integer orderNumber;

    public String getNamne() {
        return namne;
    }

    public void setNamne(String namne) {
        this.namne = namne;
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