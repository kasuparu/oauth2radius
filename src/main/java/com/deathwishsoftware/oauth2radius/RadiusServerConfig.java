package com.deathwishsoftware.oauth2radius;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RadiusServerConfig {

    @Bean
    public RadiusServerImpl getRadiusServer() {
        RadiusServerImpl server = new RadiusServerImpl();
        // TODO Get server port from application config
        server.setAuthPort(30000);
        return server;
    }

}
