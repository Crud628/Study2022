package com.lan.mybatis;

/**
 * 默认会话工厂
 * 是对数据库配置的开启会话的工厂处理类
 * 这里的工厂会操作DefaultSqlSession
 */
public class DefaultSqlSessionFactory  implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration.connection, configuration.mapperElement);
    }

}
