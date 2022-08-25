package com.lan.springbootmall.service.impl;

import com.lan.springbootmall.model.pojo.User;
import com.lan.springbootmall.model.dao.UserMapper;
import com.lan.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Keason
 * @Description:
 * @date 2022/8/24 23:09
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }
}
