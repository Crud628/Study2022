package com.lan.springframework.test.bean;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午9:54:36
 * @TODO
 * 
 */
public class UserService {
	private String uId;
	private String company;
	private String location;
	private IUserDao userDao;

	public String queryUserInfo() {
		return userDao.queryUserName(uId) + "," + company + "," + location;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
}
