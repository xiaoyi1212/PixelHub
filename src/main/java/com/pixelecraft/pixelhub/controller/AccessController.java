package com.pixelecraft.pixelhub.controller;

import com.pixelecraft.pixelhub.ServerConfig;
import com.pixelecraft.pixelhub.entity.AccessKey;
import com.pixelecraft.pixelhub.entity.LoginUser;
import com.pixelecraft.pixelhub.service.TimeService;
import com.pixelecraft.pixelhub.util.Util;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;

@RestController()
public class AccessController {

    @Autowired
    TimeService timeService;

    @RequestMapping("/access/login")
    public String onLogin(HttpServletResponse response, @RequestParam("email")String email, @RequestParam("password")String password){
        ServerConfig.DefaultUser defaultUser = ServerConfig.buildConfig().getUser();

        if(defaultUser.getEmail().equals(email) && defaultUser.getPassword().equals(password)){
            Cookie cookie = new Cookie("token",defaultUser.getUuid().toString());
            response.addCookie(cookie);
            return "/";
        }

        password = Util.getSha256Str(password);
        System.out.println("email: "+email + "||password: " + password);

        return "index.html";
    }

    @RequestMapping("/access/register")
    public String onRegister(HttpServletResponse response,
                             @RequestParam("username")String username,
                             @RequestParam("email")String email,
                             @RequestParam("password")String password){

        LoginUser user = new LoginUser();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setUuid(UUID.nameUUIDFromBytes((email + password).getBytes(StandardCharsets.UTF_8)));

        UUID uuid = UUID.randomUUID();

        String ak = String.valueOf(new Random().nextInt(90001) + 10000);

        response.addCookie(new Cookie("keyuid",uuid.toString()));
        timeService.addKey(new AccessKey(user,60,uuid,ak));

        //TODO 邮箱实现

        return "access.html";
    }

    @RequestMapping("/access/key")
    public String onKey(HttpServletRequest request, @RequestParam("key")String key){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("keyuid")){

                for(AccessKey sk: timeService.getKeys()){
                    if(sk.getUuid().toString().equals(cookie.getValue())){
                        if(sk.getKey().equals(key)){

                        }else {
                            //TODO 验证码无效
                        }
                    }
                }

            }
        }
        return "index_login.html";
    }
}
