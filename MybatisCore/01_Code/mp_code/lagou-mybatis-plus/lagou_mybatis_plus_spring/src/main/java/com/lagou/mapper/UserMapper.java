package com.lagou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    /*
        查询所有用户
     */
    public List<User> findAll();





}
