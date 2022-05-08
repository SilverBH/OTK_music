package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.otaku_music.model.Music;
import com.zsc.otaku_music.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MusicMapperTest {

    @Autowired
    MusicMapper musicMapper;

    @Test
    void insert() {
        System.out.println(musicMapper.insert(new Music(2L,"美丽之物","music163.com?id=114514","pic163.com?id=114514",114514L,1,new Date())));
    }

    @Test
    void updateById() {
        Music music = new Music(2L,"untitled world","music163.com?id=114514","pic163.com?id=114514",114514L,1,new Date());
        System.out.println(musicMapper.updateById(music));
    }

    @Test
    void deleteById() {
        System.out.println(musicMapper.deleteById(2));
    }


    @Test
    void selectById() {
        System.out.println(musicMapper.selectById(2));
    }

    @Test
    void selectList() {
        List musicList = musicMapper.selectList(new QueryWrapper<>());
        musicList.forEach(System.out::println);
    }
}