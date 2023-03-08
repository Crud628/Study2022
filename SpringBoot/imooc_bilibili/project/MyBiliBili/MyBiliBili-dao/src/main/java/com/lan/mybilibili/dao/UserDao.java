package com.lan.mybilibili.dao;

import com.lan.mybilibili.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User getUserByPhone(String phone);

    Integer addUser(User user);
}
