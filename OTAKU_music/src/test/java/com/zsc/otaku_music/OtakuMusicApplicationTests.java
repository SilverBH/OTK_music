package com.zsc.otaku_music;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zsc.otaku_music.dao.UserMapper;
import com.zsc.otaku_music.dto.UserFindDto;
import com.zsc.otaku_music.model.User;
import com.zsc.otaku_music.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class OtakuMusicApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        DataSource dataSourceMysql = applicationContext.getBean(DataSource.class);

        // 获取桌面目录作为文档存放目录, 指定生成的文件名称
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        // screw 1.0.5 版本时, cn.smallbun.screw.core.query.AbstractDatabaseQuery.getSchema 中会强转为 HikariDataSource
        // 所以只支持 HikariDataSource 数据源(Spring boot 默认 HikariCP 作为数据源.)
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/otaku_music?characterEncoding=UTF-8&serverTimezone=UTC");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("kkiimm9966");
        // 设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);


        // 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径，自己mac本地的地址，这里需要自己更换下路径
                .fileOutputDir(homeDirectory.getAbsolutePath())
                // 打开目录
                .openOutputDir(true)
                // 文件类型
                .fileType(EngineFileType.WORD)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker).build();

        // 生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder()
                .version("1.0.5")
                .description("生成文档信息描述")
                .dataSource(dataSource)
                .engineConfig(engineConfig)
                .produceConfig(getProcessConfig())
                .build();

        // 执行生成
        new DocumentationExecute(config).execute();
    }

    /**
     * 配置想要生成的表+ 配置想要忽略的表
     * @return 生成表配置
     */
    public static ProcessConfig getProcessConfig(){
        // 忽略表名
//        List<String> ignoreTableName = Arrays.asList("aa","test_group");
        // 忽略表前缀，如忽略a开头的数据库表
//        List<String> ignorePrefix = Arrays.asList("a","t");
        // 忽略表后缀
//        List<String> ignoreSuffix = Arrays.asList("_test","czb_");

        return ProcessConfig.builder()
                //根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>()).build();
//                //忽略表名
//                .ignoreTableName(ignoreTableName)
//                //忽略表前缀
//                .ignoreTablePrefix(ignorePrefix)
//                //忽略表后缀
//                .ignoreTableSuffix(ignoreSuffix).build();
    }

    @Test
    void pageQuery() {
        UserFindDto userFindDto =new UserFindDto();
        userFindDto.setCurrent(1);
        userFindDto.setPageSize(20);
        userFindDto.setPageSize(10);
        System.out.println(userMapper.pageQuery(userFindDto));
    }
}
