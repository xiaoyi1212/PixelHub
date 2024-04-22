package com.pixelecraft.pixelhub.service;

import com.pixelecraft.pixelhub.entity.DaoUser;
import com.pixelecraft.pixelhub.util.Util;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UserService {

    @Autowired
    DataBaseService db;

    public void onLogin(HttpServletResponse response, HttpSession session, String email, String password){
        password = Util.getSha256Str(password);
        DaoUser user = db.getUser(email);
        if(user != null){
            if(user.getPassword().equals(password)){
                Cookie cookie = new Cookie("pixelhub_token_id", URLEncoder.encode(user.getUuid().toString(), StandardCharsets.UTF_8));
                cookie.setMaxAge(-1);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

    public void onRegister(HttpServletResponse response,
                           String username,
                           String email,
                           String password) {
        DaoUser user;
        if ((user = db.getUser(email))!=null){
            System.out.println("email:" + email + "||" +user);
            return;
        }

        user = new DaoUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setImgt(Util.convertImageToBase64Str(Util.getResource("/static/images/admin.png")));
        db.save(user);

        Cookie cookie = new Cookie("pixelhub_token_id", URLEncoder.encode(user.getUuid().toString(), StandardCharsets.UTF_8));
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public DaoUser getLogin(HttpServletRequest request){
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("pixelhub_token_id")){
                return getUser(URLDecoder.decode(cookie.getValue(),StandardCharsets.UTF_8));
            }
        }
        return null;
    }

    public DaoUser getUser(String tokenID){
        List<DaoUser> users = db.mapper.findAll();
        for(DaoUser user : users){
            if(user.getUuid().toString().equals(tokenID)){
                return user;
            }
        }
        return null;
    }
}
