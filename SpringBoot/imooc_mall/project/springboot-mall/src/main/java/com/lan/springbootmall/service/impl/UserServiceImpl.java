package com.lan.springbootmall.service.impl;

import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.exception.MallExceptionEnum;
import com.lan.springbootmall.model.pojo.User;
import com.lan.springbootmall.model.dao.UserMapper;
import com.lan.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    @Override
    public void register(String userName, String password) throws MallException {
        // 查询用户名是否存在，不允许重名
        User result = userMapper.selectByName(userName);
        if (!ObjectUtils.isEmpty(result)) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }

        // 写到数据库
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        int count = userMapper.insertSelective(user);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.INSERT_FAILED);
        }

    }
}
