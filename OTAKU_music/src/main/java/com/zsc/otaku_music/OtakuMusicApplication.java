package com.zsc.otaku_music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.zsc.otaku_music.dao")
public class OtakuMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtakuMusicApplication.class, args);
    }

}
