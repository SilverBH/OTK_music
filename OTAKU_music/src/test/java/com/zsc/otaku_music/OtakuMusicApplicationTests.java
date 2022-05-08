package com.zsc.otaku_music;

import com.zsc.otaku_music.dao.UserMapper;
import com.zsc.otaku_music.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class OtakuMusicApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {

    }

}
