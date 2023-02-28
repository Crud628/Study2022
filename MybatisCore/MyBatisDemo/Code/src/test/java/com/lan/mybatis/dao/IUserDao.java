package com.lan.mybatis.dao;

import com.lan.mybatis.po.User;

public interface IUserDao {

    User queryUserInfoById(Long id);
}
