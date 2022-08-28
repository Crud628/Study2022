package com.lan.springbootmall.service;

import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.model.pojo.User;

import java.security.NoSuchAlgorithmException;

/**
 * @author Keason
 * @Description: userService
 * @date 2022/8/24 23:08
 */
public interface UserService {

    /**
     *
     * 取得用户
     * @return
     */
    User getUser();

    /**
     * 注册
     * @param userName 用户名
     * @param password 密码
     */
    void register(String userName, String password) throws MallException, NoSuchAlgorithmException;
}
