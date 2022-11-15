package com.lan.springframework.test.bean;
/**
 * @author Keason
 * @version 创建时间：2022年11月15日 下午11:31:09
 * @TODO
 * 
 */
public class UserService {
	private String uId;

    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId);
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
