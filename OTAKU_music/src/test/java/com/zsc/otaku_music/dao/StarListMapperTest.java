package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.otaku_music.model.Music;
import com.zsc.otaku_music.model.StarList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StarListMapperTest {

    @Autowired
    StarListMapper starListMapper;

    @Test
    void insert() {
        System.out.println(starListMapper.insert(new StarList(2L,1,new Date(),1L)));
    }

    @Test
    void updateById() {
        StarList starList = new StarList(2L,1,new Date(),1L);
        System.out.println(starListMapper.updateById(starList));
    }

    @Test
    void deleteById() {
        System.out.println(starListMapper.deleteById(2));
    }


    @Test
    void selectById() {
        System.out.println(starListMapper.selectById(2));
    }

    @Test
    void selectList() {
        List starListL = starListMapper.selectList(new QueryWrapper<>());
        starListL.forEach(System.out::println);
    }
}