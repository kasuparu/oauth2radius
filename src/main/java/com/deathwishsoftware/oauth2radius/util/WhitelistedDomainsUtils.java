package com.deathwishsoftware.oauth2radius.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class WhitelistedDomainsUtils {

    private static Logger logger = Logger.getLogger(WhitelistedDomainsUtils.class);

    private String[] urls;

    public WhitelistedDomainsUtils(@Value("${oauth2radius.whitelisted-domains}") String[] urls) {
        this.urls = urls;
    }

    @PostConstruct
    public void printDomains() {
        if (urls.length > 0) {
            logger.info(String.format("%s", Arrays.toString(urls)));
        } else {
            logger.error("No whitelisted domains specified, credential creation is impossible");
        }
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
