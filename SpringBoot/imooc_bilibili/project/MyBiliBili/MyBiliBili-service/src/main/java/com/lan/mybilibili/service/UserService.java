package com.lan.mybilibili.service;

import com.lan.mybilibili.dao.UserDao;
import com.lan.mybilibili.domain.User;
import com.lan.mybilibili.domain.UserInfo;
import com.lan.mybilibili.domain.constant.UserConstant;
import com.lan.mybilibili.domain.exception.ConditionException;
import com.lan.mybilibili.service.util.MD5Util;
import com.lan.mybilibili.service.util.RSAUtil;
import com.lan.mybilibili.service.util.TokenUtil;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
        // 获取手机号 用于判断和获取用户信息
        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new ConditionException("手机号不能为空");
        }

        // 获取用户
        User dbUser = this.getUserByPhone(phone);
        if (dbUser != null) {
            throw new ConditionException("该手机号已经注册");
        }

        // 系统时间 用于md5加密
        Date now = new Date();
        String salt = String.valueOf(now.getTime());
        String password = user.getPassword();
        //  加密后的密码
        String rawPassWord;
        //  加密
        try {
            rawPassWord = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("密码解密失败");
        }
        String md5Password = MD5Util.sign(rawPassWord, salt, "UTF-8");
        user.setSalt(salt);
        user.setPassword(md5Password);
        user.setCreateTime(now);
        userDao.addUser(user);
        // 添加用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNick(UserConstant.DEFAULT_NICK);
        userInfo.setBirth(UserConstant.DEFAULT_BIRTH);
        userInfo.setGender(UserConstant.GENDER_MALE);
        userInfo.setCreateTime(now);
        userDao.addUserInfo(userInfo);
    }

    /**
     * 通过手机号获取用户信息
     *
     * @param phone 手机号
     * @return 用户
     */
    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

    public String login(User user) throws Exception {
        // 获取手机号 用于判断和获取用户信息
        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new ConditionException("手机号不能为空");
        }

        // 获取用户
        User dbUser = this.getUserByPhone(phone);
        if (dbUser == null) {
            throw new ConditionException("当前用户不存在");
        }
        String password = user.getPassword();
        String rawPassword;
        try{
            rawPassword = RSAUtil.decrypt(password);
        }catch (Exception e){
            throw new ConditionException("密码解密失败！");
        }
        String salt = dbUser.getSalt();
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        if(!md5Password.equals(dbUser.getPassword())){
            throw new ConditionException("密码错误！");
        }
        return TokenUtil.generateToken(dbUser.getId());
    }

    /**
     * 根据用户ID获取用户信息
     * @param userId
     * @return
     */
    public String getRefreshTokenByUserId(Long userId) {
        return userDao.getRefreshTokenByUserId(userId);
    }

    /**
     * 根据用户ID获取用户详细信息
     * @param userId
     * @return
     */
    public User getUserInfo(Long userId) {
        User user = userDao.getUserById(userId);
        UserInfo userInfo = userDao.getUserInfoByUserId(userId);
        user.setUserInfo(userInfo);
        return user;
    }

    public void updateUsers(User user) throws Exception{
        Long id = user.getId();
        User dbUser = userDao.getUserById(id);
        if(dbUser == null){
            throw new ConditionException("用户不存在！");
        }
        if(!StringUtils.isNullOrEmpty(user.getPassword())){
            String rawPassword = RSAUtil.decrypt(user.getPassword());
            String md5Password = MD5Util.sign(rawPassword, dbUser.getSalt(), "UTF-8");
            user.setPassword(md5Password);
        }
        user.setUpdateTime(new Date());
        userDao.updateUsers(user);
    }

    public void updateUserInfos(UserInfo userInfo) {
        userInfo.setUpdateTime(new Date());
        userDao.updateUserInfos(userInfo);
    }
}
