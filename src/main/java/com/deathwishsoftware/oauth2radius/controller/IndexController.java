package com.deathwishsoftware.oauth2radius.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}
