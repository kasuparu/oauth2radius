package com.deathwishsoftware.oauth2radius.util;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.LinkedHashMap;

public class OAuth2AuthenticationUtils {

    public static AuthenticationProperties extractAuthenticationProperties(OAuth2Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        @SuppressWarnings("unchecked")
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) auth.getUserAuthentication().getDetails();
        return new AuthenticationProperties(
            (String)map.get("email"),
            (String)map.get("name"),
            (String)map.get("picture")
        );
    }

}
