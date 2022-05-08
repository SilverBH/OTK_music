package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.otaku_music.model.MusicList;
import com.zsc.otaku_music.model.MusicListItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MusicListItemMapperTest {

    @Autowired
    MusicListItemMapper musicListItemMapper;

    @Test
    void insert() {
        System.out.println(musicListItemMapper.insert(new MusicListItem(2L,1,new Date(),1L)));
    }

    @Test
    void updateById() {
        MusicListItem musicListItem = new MusicListItem(2L,1,new Date(),1L);
        System.out.println(musicListItemMapper.updateById(musicListItem));
    }

    @Test
    void deleteById() {
        System.out.println(musicListItemMapper.deleteById(2));
    }


    @Test
    void selectById() {
        System.out.println(musicListItemMapper.selectById(2));
    }

    @Test
    void selectList() {
        List musicListItemList = musicListItemMapper.selectList(new QueryWrapper<>());
        musicListItemList.forEach(System.out::println);
    }
}