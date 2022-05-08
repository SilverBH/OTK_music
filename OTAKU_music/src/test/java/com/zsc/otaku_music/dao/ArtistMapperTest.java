package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.otaku_music.model.Artist;
import com.zsc.otaku_music.model.MusicList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArtistMapperTest {

    @Autowired
    ArtistMapper artistMapper;

    @Test
    void insert() {
        System.out.println(artistMapper.insert(new Artist(2L,"nano",1,new Date())));
    }

    @Test
    void updateById() {
        Artist artist = new Artist(2L,"nano",1,new Date());
        System.out.println(artistMapper.updateById(artist));
    }

    @Test
    void deleteById() {
        System.out.println(artistMapper.deleteById(2));
    }


    @Test
    void selectById() {
        System.out.println(artistMapper.selectById(2));
    }

    @Test
    void selectList() {
        List artistList = artistMapper.selectList(new QueryWrapper<>());
        artistList.forEach(System.out::println);
    }
}