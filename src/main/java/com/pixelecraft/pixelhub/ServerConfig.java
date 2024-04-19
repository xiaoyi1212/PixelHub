package com.pixelecraft.pixelhub;

import com.pixelecraft.pixelhub.entity.HubUser;
import com.pixelecraft.pixelhub.util.Configuration;
import com.pixelecraft.pixelhub.util.Util;
import com.pixelecraft.pixelhub.util.YamlConfiguration;
import lombok.Getter;

import java.io.*;

public class ServerConfig {
    Configuration configuration;

    @Getter
    String ip;
    @Getter
    int port;

    @Getter
    Repository repository;

    @Getter
    DefaultUser user;

    private ServerConfig(File file) throws IOException {
        configuration = new YamlConfiguration().load(file);
        this.repository = new Repository();
        this.user = new DefaultUser();
        loadConfig();
    }

    private void loadConfig(){
        this.port = configuration.get("server.port",8080);
        this.ip = configuration.getString("server.ip","localhost");
        this.repository.repo_root = configuration.get("repository.root","repos");
        this.user.email = configuration.get("admin.username","Administrator");
        this.user.email = configuration.get("admin.email","admin@pixel.com");
        this.user.email = configuration.get("admin.password","pixel114514");
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

    private static class DefaultUser extends HubUser {
        String username,email,password;

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public String getPassword() {
            return password;
        }
    }

    public static class Repository {
        String repo_root;
        String db_path;
        File repo_root_dir;

        public void setDbPath(String db_path) {
            this.db_path = db_path;
        }

        public void setRepoRootDir(File repo_root_dir) {
            this.repo_root_dir = repo_root_dir;
        }

        public void setRepoRoot(String repo_root) {
            this.repo_root = repo_root;
        }

        public String getRepoRoot() {
            return repo_root;
        }

        public File getRepoRootDir() {
            return repo_root_dir;
        }

        public String getDbPath() {
            return db_path;
        }
    }
}
