package com.pixelecraft.pixelhub.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String onIndex(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("usertoken")){

                }
            }
        }
        return "index_nologin.html";
    }

    @RequestMapping("/signin")
    public String onLogin(HttpServletRequest request){
        return "login.html";
    }

    @RequestMapping("/signup")
    public String onRegister(HttpServletRequest request){
        return "register.html";
    }
}
