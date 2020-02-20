package com.deathwishsoftware.oauth2radius.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix="internationalization")
public class InternationalizationProperties {

    private Map<String, String> messages = new HashMap<>();

    public Map<String, String> getMessages() {
        return messages;
    }
}
