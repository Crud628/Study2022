package com.lan.springframework.test.bean;

/**
 * 
 * @author Keason
 * @description 模拟含有入参构造函数的用户 Bean 对象
 * @since 0.01
 */
public class UserService {

    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(name);
        return sb.toString();
    }

}
