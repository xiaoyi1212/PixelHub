package com.pixelecraft.pixelhub.util;

import com.pixelecraft.pixelhub.ServerConfig;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
    public static InputStream getResource(String path) {
        String pathx;
        if (path.startsWith("/")) pathx = path;
        else pathx = "/" + path;
        return Util.class.getResourceAsStream(pathx);
    }

    public static void createRepoDir(ServerConfig config){
        File file = new File(config.getRepository().getRepoRoot());
        if(!file.exists()){
            file.mkdir();
            config.getRepository().setRepoRootDir(file);
            return;
        }
        if(!file.isDirectory()){
            System.err.println("Cannot create repository root directory. Please delete 'repos' file.");
            System.exit(-1);
        }
        config.getRepository().setRepoRootDir(file);
    }

    public static String getSha256Str(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }
}
