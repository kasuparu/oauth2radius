package com.deathwishsoftware.oauth2radius.controller;

import com.deathwishsoftware.oauth2radius.persistence.RadCheckService;
import com.deathwishsoftware.oauth2radius.util.AuthenticationProperties;
import com.deathwishsoftware.oauth2radius.util.OAuth2AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class IndexController {

    @Autowired
    private RadCheckService radCheckService;

    @GetMapping("/")
    public String index(Model model, OAuth2Authentication auth) {
        model.addAttribute("isAuthenticated", auth != null && auth.isAuthenticated());

        if (auth != null && auth.isAuthenticated()) {
            AuthenticationProperties properties = OAuth2AuthenticationUtils.extractAuthenticationProperties(auth);
            if (properties != null) {

                String password = this.radCheckService.getUserPassword(properties.getEmail());

                model.addAttribute("picture", properties.getPictureUrl());
                model.addAttribute("name", properties.getName());
                model.addAttribute("email", properties.getEmail());
                model.addAttribute("password", password);
            }
        }

        return "index";
    }

}
