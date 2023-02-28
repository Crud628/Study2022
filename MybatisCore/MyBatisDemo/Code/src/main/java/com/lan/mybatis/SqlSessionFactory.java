package com.lan.mybatis;

/**
 * 会话工厂接口
 */
public interface SqlSessionFactory {

    /**
     *
     * @return
     */
    SqlSession openSession();
}
