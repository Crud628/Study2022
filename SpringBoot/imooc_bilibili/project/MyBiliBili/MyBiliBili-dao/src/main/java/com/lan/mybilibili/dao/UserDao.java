package com.lan.mybilibili.dao;

import com.lan.mybilibili.domain.User;
import com.lan.mybilibili.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserDao {

    User getUserByPhone(String phone);

    Integer addUser(User user);

    void addUserInfo(UserInfo userInfo);

    String getRefreshTokenByUserId(Long userId);

    User getUserById(Long userId);

    UserInfo getUserInfoByUserId(Long userId);

    void updateUsers(User user);

    void updateUserInfos(UserInfo userInfo);

    List<UserInfo> getUserInfoByUserIds(Set<Long> userIdList);
}
