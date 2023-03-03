package com.lan.mybatis;

import java.util.List;

/**
 * 会话接口
 * 对数据库进行定义和处理的类，包括常用的方法
 * 这里定义了对数据库操作的查询接口。分为查询一个结果和多个结果，同时包含有参和无参
 * 在MyBatis中，所有的SQL语句的执行是从开启SqlSession会话开始的，之后创建执行器。
 * 为了简化，这里直接在SqlSession会话类实现类DefaultSqlSession中操作数据库并执行SQL语句。
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
