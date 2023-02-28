package com.lan.mybatis;

import java.util.List;

/**
 * 会话接口
 */
public interface SqlSession {

    /**
     * 查询一个结果（无参）
     *
     * @param statement
     * @return
     * @param <T>
     */
    <T> T selectOne(String statement);

    /**
     * 查询一个结果（有参）
     *
     * @param statement
     * @param parameter
     * @return
     * @param <T>
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * 查询一个list（无参）
     *
     * @param statement
     * @return
     * @param <T>
     */
    <T> List<T> selectList(String statement);

    /**
     * 查询一个list（有参）
     *
     * @param statement
     * @param parameter
     * @return
     * @param <T>
     */
    <T> List<T> selectList(String statement, Object parameter);

    /**
     * 关闭
     */
    void close();
}
