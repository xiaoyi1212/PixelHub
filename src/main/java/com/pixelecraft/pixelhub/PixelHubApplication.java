package com.pixelecraft.pixelhub;

import com.pixelecraft.pixelhub.util.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PixelHubApplication {

    public static ServerConfig config;


    /*
    public void test() throws Exception {
        // 1、首先创建数据表
        String ddl = """
            CREATE TABLE `user` (
                id INTEGER PRIMARY KEY NOT NULL,
                name TEXT,
                create_at TEXT
            );
        """;

        this.jdbcTemplate.execute(ddl);

        // 2、插入一条数据
        int ret = this.jdbcTemplate.update("INSERT INTO `user` (`id`, `name`, `create_at`) VALUES (?, ?, ?);", new Object[] {1, "springdoc", LocalDateTime.now()});

        log.info("插入数据：{}", ret);

        // 3、检索一条数据
        Map<String, Object> user = this.jdbcTemplate.queryForObject("SELECT * FROM `user` WHERE `id` = ?", new ColumnMapRowMapper(), 1L);

        log.info("检索数据：{}", user);
    }
    */


    @Bean
    public TomcatServletWebServerFactory serverFactory(){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(config.getPort());
        return factory;
    }

    public static void main(String[] args) {
        config = ServerConfig.buildConfig();
        Util.createRepoDir(config);
        SpringApplication.run(PixelHubApplication.class, args);
    }

}
