package com.lan.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午10:02:15
 * @TODO
 * 
 */
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
