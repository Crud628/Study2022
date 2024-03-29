package com.lan.springbootmall.model.dao;

import com.lan.springbootmall.model.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名
     */
    User selectByName(String userName);

    /**
     * 用户登录
     *
     * @param userName 账户名
     * @param password 密码
     * @return
     */
    User selectLogin(@Param("userName") String userName, @Param("password") String password);
}