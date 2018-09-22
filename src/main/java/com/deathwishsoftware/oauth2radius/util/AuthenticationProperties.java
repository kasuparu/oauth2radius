package com.deathwishsoftware.oauth2radius.util;

public class AuthenticationProperties {

    private final String email;
    private final String name;
    private final String pictureUrl;

    public AuthenticationProperties(String email, String name, String pictureUrl) {
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}
