package com.pixelecraft.pixelhub;

import com.pixelecraft.pixelhub.util.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PixelHubApplication {

    public static ServerConfig config;

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
