package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.otaku_music.model.User;
import com.zsc.otaku_music.model.UserAuthority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAuthorityMapperTest {

    @Autowired
    UserAuthorityMapper userAuthorityMapper;

    @Test
    void insert() {
        System.out.println(userAuthorityMapper.insert(new UserAuthority(1L,1L,1L)));
    }

    @Test
    void updateById() {
        UserAuthority userAuthority = new UserAuthority(1L,1L,2L);
        System.out.println(userAuthorityMapper.updateById(userAuthority));
    }

    @Test
    void deleteById() {
        System.out.println(userAuthorityMapper.deleteById(1));
    }

    @Test
    void selectById() {
        System.out.println(userAuthorityMapper.selectById(1));
//        User user = new User(2L,"satori","123","satori@163.com",new Date(),"127.0.0.1",new Date(),"0.0.0.0",1,"",1L);
//        System.out.println(userMapper.selectOne(new QueryWrapper<User>(user)));  //null
    }

    @Test
    void selectList() {
        // 查询全部
        List userAuthList = userAuthorityMapper.selectList(new QueryWrapper<>());
        userAuthList.forEach(System.out::println);
    }
}