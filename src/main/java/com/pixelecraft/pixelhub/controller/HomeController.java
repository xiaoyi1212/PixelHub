package com.pixelecraft.pixelhub.controller;

import com.pixelecraft.pixelhub.PixelHubApplication;
import com.pixelecraft.pixelhub.ServerConfig;
import com.pixelecraft.pixelhub.entity.DaoUser;
import com.pixelecraft.pixelhub.service.UserService;
import com.pixelecraft.pixelhub.service.ViewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    ViewService viewService;

    @RequestMapping("/")
    public String onIndex(HttpServletRequest request, Model model) {
        DaoUser user = userService.getLogin(request);
        viewService.viewUser(user,model);
        return "index.html";
    }

    @RequestMapping("/signin")
    public String onLogin(HttpServletRequest request) {
        return "login.html";
    }

    @RequestMapping("/signup")
    public String onRegister(HttpServletRequest request) {
        return "register.html";
    }
}
