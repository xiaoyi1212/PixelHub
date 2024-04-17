package com.pixelecraft.pixelhub;

import com.pixelecraft.pixelhub.util.Configuration;
import com.pixelecraft.pixelhub.util.Util;
import com.pixelecraft.pixelhub.util.YamlConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.logging.Logger;

public class ServerConfig {
    Configuration configuration;

    @Getter
    String ip;
    @Getter
    int port;
    @Getter
    String repo_root;

    @Getter
    @Setter
    File repo_root_dir;

    private ServerConfig(File file) throws IOException {
        configuration = new YamlConfiguration().load(file);
        loadConfig();
    }

    private void loadConfig(){
        this.port = configuration.get("server.port",8080);
        this.ip = configuration.getString("server.ip","localhost");
        this.repo_root = configuration.get("repository.root","repos");
    }

    public static ServerConfig buildConfig(){
        try{
            File file = new File("./config.yml");
            createConfigFile(file);
            return new ServerConfig(file);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static void createConfigFile(File file) throws IOException {
        if(!file.exists()) {
            file.createNewFile();
            try(BufferedOutputStream target = new BufferedOutputStream(new FileOutputStream(file))){
                InputStream source = Util.getResource("config.yml");
                byte[] buf = new byte[8192];
                int length;
                while ((length = source.read(buf)) > 0) {
                    target.write(buf, 0, length);
                }
            }
            System.out.println("Please check 'config.yml'. And restart your git server.");
            System.exit(0);
        }
    }
}
