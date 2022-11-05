package com.lagou.test;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.lagou.mapper.UserMapper;
import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MPTest {

    @Test
    public void mybatisTest() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // List<User> all = mapper.findAll();
        List<User> users = mapper.selectList(null);

        for (User user : users) {
            System.out.println(user);
        }


    }


}
