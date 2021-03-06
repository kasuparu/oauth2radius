package com.deathwishsoftware.oauth2radius.controller;

import com.deathwishsoftware.oauth2radius.persistence.RadCheckService;
import com.deathwishsoftware.oauth2radius.util.AuthenticationProperties;
import com.deathwishsoftware.oauth2radius.util.OAuth2AuthenticationUtils;
import com.deathwishsoftware.oauth2radius.util.WhitelistedDomainsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class IndexController {

    @Autowired
    private RadCheckService radCheckService;

    @Autowired
    private WhitelistedDomainsUtils whitelistedDomainsUtils;

    @GetMapping("/")
    public String index(Model model, OAuth2Authentication auth) {
        boolean isAuthenticated = auth != null && auth.isAuthenticated();
        model.addAttribute("isAuthenticated", isAuthenticated);

        if (!isAuthenticated) {
            return "index";
        }

        AuthenticationProperties properties = OAuth2AuthenticationUtils.extractAuthenticationProperties(auth);

        if (properties == null) {
            return "index";
        }

        String email = properties.getEmail();
        boolean isAllowedDomain = whitelistedDomainsUtils.isAllowed(properties.getEmail());
        model.addAttribute("isAllowedDomain", isAllowedDomain);

        if (!isAllowedDomain) {
            return "index";
        }

        String password = this.radCheckService.getUserPassword(email);

        model.addAttribute("picture", properties.getPictureUrl());
        model.addAttribute("name", properties.getName());
        model.addAttribute("email", properties.getEmail());
        model.addAttribute("password", password);

        return "index";
    }

}
