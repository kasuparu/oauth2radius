package com.deathwishsoftware.oauth2radius.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WhitelistedDomainsUtils {

    private String[] urls;

    public WhitelistedDomainsUtils(@Value("${oauth2radius.whitelisted-domains}") String[] urls) {
        this.urls = urls;
    }

    public boolean isAllowed(String email) {
        String domain = getDomain(email);
        if ("".equals(domain)) {
            return false;
        }
        for (String url : urls) {
            if (url.toLowerCase().equals(domain)) {
                return true;
            }
        }
        return false;
    }

    private String getDomain(String email) {
        if (!email.contains("@")) {
            return "";
        }
        return email.substring(email.indexOf("@") + 1).toLowerCase();
    }
}
