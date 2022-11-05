package com.lagou.lagoumpspringboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lagou.mapper.UserMapper;
import com.lagou.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class LagouMpSpringbootApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testFindAll(){

        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }




    @Test
    void testSelect() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }



    /*
        测试添加
     */
    @Test
    public void testInsert(){

        User user = new User();
        user.setName("应颠22");
        user.setAge(20);
        user.setMail("zimu@lagou.com");

        // 返回值是影响的行数
        int result = userMapper.insert(user);
        System.out.println(result);

        System.out.println("id值为" + user.getId());

    }

    /*
        测试根据ID进行修改
     */
    @Test
    public void testUpateById(){

        User user = new User();
        user.setId(6L);
        user.setAge(30);

        int i = userMapper.updateById(user);
        System.out.println(i);

    }


    /*
     测试根据条件进行修改
  */
    @Test
    public void testUpate(){

        // 1. 更新的字段
        User user = new User();
        user.setAge(35);

        // 2.更新的条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","子慕");

        int i = userMapper.update(user,queryWrapper);
        System.out.println(i);
    }

    @Test
    public void testUpate2(){

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",6).set("age",40);


        int i = userMapper.update(null,updateWrapper);
        System.out.println(i);
    }

    /*
        根据ID进行删除
     */
    @Test
    public void testDeleteById(){

        int i = userMapper.deleteById(2L);
        System.out.println(i);
    }


    /*
      根据columnMap进行删除
   */
    @Test
    public void testDeleteByMap(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("name","子慕");
        map.put("age",18);

        // 将columnMap中的元素设置为删除的条件，多个条件是and的关系
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }



    /*
       调用delete进行删除
    */
    @Test
    public void testDelete(){


        User user = new User();
        user.setName("子慕2");
        user.setAge(18);

        QueryWrapper<User> ueryWrapper = new QueryWrapper<>(user);
       // ueryWrapper.eq("name","子慕1").eq("age",18);

        int i = userMapper.delete(ueryWrapper);
        System.out.println(i);
    }


    /*
         调用deleteBatchIds进行批量删除
    */
    @Test
    public void testDeleteBatchIds(){

        int i = userMapper.deleteBatchIds(Arrays.asList(10l,11l));
        System.out.println(i);
    }

    /*
        根据ID进行查询
     */
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(2L);
        System.out.println(user);
    }

    /*
      根据ID进行批量查询
   */
    @Test
    public void testSelectBatchIds(){

        List<User> users = userMapper.selectBatchIds(Arrays.asList(12L, 13l));
        for (User user : users) {
            System.out.println(user);
        }
    }


    /*
       测试selectOne
    */
    @Test
    public void testSelectOne(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","子慕");

        // 根据条件查询一条记录，如果查询结果超过一条，会报错
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);

    }


    /*
        根据wrapper条件，查询总记录数
     */
    @Test
    public void testSelectCount(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",18); // 查询年龄大于18的

        // 根据条件查询一条记录，如果查询结果超过一条，会报错
        Integer count = userMapper.selectCount(queryWrapper);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(count);
        for (User user : users) {
            System.out.println(user);
        }

    }


    /*
     分页查询
  */
    @Test
    public void testSelectPage(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",18); // 查询年龄大于18的

        // 第一个参数：当前页   第二个参数：每页显示条数
        Page<User> page = new Page<>(2, 2);


        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("总条数" + userIPage.getTotal());
        System.out.println("总页数" + userIPage.getPages());

        System.out.println("分页数据" + userIPage.getRecords());


    }


    /*
        测试自定义方法findById
     */

    @Test
    public void findById(){
        User user = userMapper.findById(12L);
        System.out.println(user);
    }


    /*
        测试条件构建器 allEq
     */
    @Test
    public void testAllEq(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // 构建map
        Map<String, Object> map = new HashMap<>();
        map.put("name","jack");
        map.put("age",null);

        //  WHERE name = ? AND age IS NULL
        // queryWrapper.allEq(map);

        // WHERE name = ?
        //  queryWrapper.allEq(map,false);

        // SELECT id,name,age,email AS mail,user_name FROM tb_user
       // queryWrapper.allEq(false,map,true);

        // WHERE age IS NULL
        queryWrapper.allEq((k,v) -> k.equals("name"),map);

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }


    }



    /*
        基本比较操作
     */
    @Test
    public void testWrapper(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

       // WHERE email = ? AND age >= ? AND name IN (?,?)
        queryWrapper.eq("email","zimu@lagou.com")
                .ge("age",20)
                .in("name","子慕","应颠");

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }


    }



    /*
        模糊查询
    */
    @Test
    public void testWrapperLike(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like("name","子");

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }


    }



    /*
        排序查询、逻辑查询、select
    */
    @Test
    public void testWrapper2(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

       // queryWrapper.orderByDesc("age");

        queryWrapper.eq("name","jack").or().eq("age",28).select("name");


        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }


    }











}
