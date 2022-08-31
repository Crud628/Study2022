package com.lan.springbootmall.service;

import com.github.pagehelper.PageInfo;
import com.lan.springbootmall.model.request.CreateOrderReq;
import com.lan.springbootmall.model.vo.OrderVO;

/**
 * @author Keason
 * @Description:
 * @date 2022/8/31 14:32
 */
public interface OrderService {
    String create(CreateOrderReq createOrderReq);

    OrderVO detail(String orderNo);

    PageInfo listForCustomer(Integer pageNum, Integer pageSize);

    void cancel(String orderNo);

    String qrcode(String orderNo);

    void pay(String orderNo);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    void deliver(String orderNo);

    void finish(String orderNo);
}
