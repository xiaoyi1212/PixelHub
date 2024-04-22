package com.pixelecraft.pixelhub.controller;

import com.pixelecraft.pixelhub.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class AccessController {

    @Autowired
    UserService service;

    @RequestMapping("/access/login")
    public String onLogin(HttpServletResponse response,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password,
                          HttpSession session) {
        if((email == null || email.trim().isEmpty())||(password == null || password.trim().isEmpty()))
            return "redirect:/signin";
        service.onLogin(response, session, email, password);
        return "redirect:/";
    }

    @RequestMapping("/access/register")
    public String onRegister(HttpServletResponse response,
                             @RequestParam("username") String username,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password) {
        service.onRegister(response, username, email, password);
        return "redirect:/";
    }
}
