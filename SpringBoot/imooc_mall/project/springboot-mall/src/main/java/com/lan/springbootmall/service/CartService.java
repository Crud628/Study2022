package com.lan.springbootmall.service;

import com.lan.springbootmall.model.vo.CartVO;

import java.util.List;

/**
 * @author Keason
 * @Description:
 * @date 2022/8/31 14:22
 */
public interface CartService {
    List<CartVO> list(Integer userId);

    List<CartVO> add(Integer userId, Integer productId, Integer count);

    List<CartVO> update(Integer userId, Integer productId, Integer count);

    List<CartVO> delete(Integer userId, Integer productId);

    List<CartVO> selectOrNot(Integer userId, Integer productId, Integer selected);

    List<CartVO> selectAllOrNot(Integer userId, Integer selected);
}
