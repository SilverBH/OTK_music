package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.otaku_music.model.Authority;
import com.zsc.otaku_music.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorityMapperTest {
    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthorityMapper authorityMapper;

    @Test
    void insert() {
        System.out.println(authorityMapper.insert(new Authority(3L,"ROLE_test")));
    }

    @Test
    void updateById() {
        Authority authority = new Authority(3L,"ROLE_test");
        System.out.println(authorityMapper.updateById(authority));
    }

    @Test
    void deleteById() {
        System.out.println(authorityMapper.deleteById(3));
    }

    @Test
    void selectById() {
        System.out.println(authorityMapper.selectById(3));
    }

    @Test
    void selectList() {
        List authorityList = authorityMapper.selectList(new QueryWrapper<>());
        authorityList.forEach(System.out::println);
    }

    @Test
    void findAuthorityByUserName() {
        System.out.println(userMapper.insert(new User(2L,"satori","123","satori@163.com",new Date(),"127.0.0.1",new Date(),"0.0.0.0",1,"")));
        System.out.println(userMapper.insert(new User(1L,"yooomu","123","yooomu@163.com",new Date(),"127.0.0.1",new Date(),"0.0.0.0",1,"")));

        List<Authority> authorityList = authorityMapper.findAuthorityByUserName("satori");
        authorityList.forEach(System.out::println);
    }
}