package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.otaku_music.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void insert() {
        System.out.println(userMapper.insert(new User(2L,"satori","123","satori@163.com",new Date(),"127.0.0.1",new Date(),"0.0.0.0",1,"")));
//        System.out.println(userMapper.insert(new User(1L,"yooomu","123","yooomu@163.com",new Date(),"127.0.0.1",new Date(),"0.0.0.0",1,"")));
    }

    @Test
    void updateById() {
        User user = new User(2L,"satori","123456","satori@163.com",new Date(),"127.0.0.1",new Date(),"0.0.0.0",1,"");
        System.out.println(userMapper.updateById(user));
    }

    @Test
    void deleteById() {
        System.out.println(userMapper.deleteById(2));
    }

    @Test
    void selectById() {
        System.out.println(userMapper.selectById(2));
//        User user = new User(2L,"satori","123","satori@163.com",new Date(),"127.0.0.1",new Date(),"0.0.0.0",1,"",1L);
//        System.out.println(userMapper.selectOne(new QueryWrapper<User>(user)));  //null
    }

    @Test
    void selectList() {
        // 查询全部
//        List userList = userMapper.selectList(new QueryWrapper<>());
        // 根据用户名查询
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name","satori");

        List userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    void selectOne() {
        // 根据用户名查询
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name","satori");

        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }
}