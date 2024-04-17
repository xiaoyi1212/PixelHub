package com.pixelecraft.pixelhub.util;

import com.pixelecraft.pixelhub.ServerConfig;

import java.io.File;
import java.io.InputStream;

public class Util {
    public static InputStream getResource(String path) {
        String pathx;
        if (path.startsWith("/")) pathx = path;
        else pathx = "/" + path;
        return Util.class.getResourceAsStream(pathx);
    }

    public static void createRepoDir(ServerConfig config){
        File file = new File(config.getRepo_root());
        if(!file.exists()){
            file.mkdir();
            config.setRepo_root_dir(file);
            return;
        }
        if(!file.isDirectory()){
            System.err.println("Cannot create repository root directory. Please delete 'repos' file.");
            System.exit(-1);
        }
        config.setRepo_root_dir(file);
    }
}
