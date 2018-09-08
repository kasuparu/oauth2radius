package com.deathwishsoftware.oauth2radius.radius;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tinyradius.util.RadiusClient;

@Configuration
public class RadiusClientConfig {

    @Value("${oauth2radius.auth-port:0}")
    private int authPort;

    @Bean
    public RadiusClient getRadiusClient() {
        RadiusClient client = new RadiusClient("localhost", "shared-secret");
        if (authPort > 1 && authPort < 65535) {
            client.setAuthPort(this.authPort);
        }
        return client;
    }

}
