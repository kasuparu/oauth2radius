package com.deathwishsoftware.oauth2radius.controller;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.LinkedHashMap;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, OAuth2Authentication auth) {
        model.addAttribute("isAuthenticated", auth != null && auth.isAuthenticated());

        if (auth != null && auth.isAuthenticated()) {
            LinkedHashMap<String, Object> properties = extractAuthenticationProperties(auth);
            if (properties != null) {
                model.addAttribute("picture", properties.get("picture"));
                model.addAttribute("name", properties.get("name"));
                model.addAttribute("email", properties.get("email"));
                model.addAttribute("password", "password");
            }
        }

        return "index";
    }

    protected LinkedHashMap<String, Object> extractAuthenticationProperties(OAuth2Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        @SuppressWarnings("unchecked")
        LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) auth.getUserAuthentication().getDetails();
        return properties;
    }

}
