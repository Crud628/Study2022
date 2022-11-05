package com.lagou.mapper;

import com.lagou.pojo.User;

public interface UserMapper extends MyBaseMapper<User> {


    /*
        自定义findById方法
     */
    public User findById(Long id);


}
