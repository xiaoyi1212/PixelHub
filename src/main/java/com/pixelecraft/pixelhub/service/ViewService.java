package com.pixelecraft.pixelhub.service;

import com.pixelecraft.pixelhub.entity.DaoUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ViewService {
    public void viewUser(DaoUser user, Model model){
        if(user == null) {
            viewExample(model);
            return;
        }
        model.addAttribute("data", "<ul class=\"navbar-nav m-lg-auto\">\n" +
                "                    <li class=\"nav-item pl-1\">\n" +
                "                        <a class=\"nav-link\"><img class=\"rounded-circle\" src=\"data:image/png;base64," + user.getImgt() + "\"> " + user.getUsername() + "</a>\n" +
                "                    </li>\n" +
                "                </ul>");
    }

    public void viewExample(Model model){
        model.addAttribute("data", "<ul class=\"navbar-nav m-lg-auto gx-2\">\n" +
                "                    <li class=\"nav-item p-lg-1\">\n" +
                "                        <a class=\"btn btn-outline-dark\" href=\"/signin\">SignIn</a>\n" +
                "                    </li>\n" +
                "                    <li class=\"nav-item p-lg-1\">\n" +
                "                        <a class=\"btn btn-outline-dark\" href=\"/signup\">SignUp</a>\n" +
                "                    </li>\n" +
                "                </ul>");
    }
}
