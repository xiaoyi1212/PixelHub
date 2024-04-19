package com.pixelecraft.pixelhub.controller;

import com.pixelecraft.pixelhub.util.Util;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AccessController {

    @RequestMapping("/access/login")
    public String onLogin(@RequestParam("email")String email, @RequestParam("password")String password){
        password = Util.getSha256Str(password);System.out.println("email: "+email + "||password: " + password);

        return "index.html";
    }

    @RequestMapping("/access/register")
    public String onRegister(HttpServletRequest request,
                             @RequestParam("username")String username,
                             @RequestParam("email")String email,
                             @RequestParam("password")String password){

        return "index.html";
    }
}
