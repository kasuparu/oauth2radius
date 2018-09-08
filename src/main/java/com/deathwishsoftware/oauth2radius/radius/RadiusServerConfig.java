package com.deathwishsoftware.oauth2radius.radius;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RadiusServerConfig {

    @Value("${oauth2radius.auth-port:0}")
    private int authPort;

    @Bean
    public RadiusServerImpl getRadiusServer() {
        RadiusServerImpl server = new RadiusServerImpl();
        if (authPort > 1 && authPort < 65535) {
            server.setAuthPort(this.authPort);
        }
        return server;
    }

}
