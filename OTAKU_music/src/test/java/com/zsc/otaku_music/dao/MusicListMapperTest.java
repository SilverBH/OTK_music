package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.otaku_music.model.Music;
import com.zsc.otaku_music.model.MusicList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MusicListMapperTest {

    @Autowired
    MusicListMapper musicListMapper;

    @Test
    void insert() {
        System.out.println(musicListMapper.insert(new MusicList(2L,"本地音乐",1,new Date(),1L)));
    }

    @Test
    void updateById() {
        MusicList musicList = new MusicList(2L,"喜欢的音乐",1,new Date(),1L);
        System.out.println(musicListMapper.updateById(musicList));
    }

    @Test
    void deleteById() {
        System.out.println(musicListMapper.deleteById(2));
    }


    @Test
    void selectById() {
        System.out.println(musicListMapper.selectById(2));
    }

    @Test
    void selectList() {
        List musicLList = musicListMapper.selectList(new QueryWrapper<>());
        musicLList.forEach(System.out::println);
    }
}