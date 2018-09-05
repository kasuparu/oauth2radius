package com.deathwishsoftware.oauth2radius;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RadiusServerConfig {

    @Value("${application.authPort:0}")
    protected String authPortStr;

    @Bean
    public RadiusServerImpl getRadiusServer() {
        RadiusServerImpl server = new RadiusServerImpl();
        int authPort = 0;
        try {
            authPort = Integer.parseInt(authPortStr);
        } catch (Exception e) {
            // TODO Log an error
        } finally {
            if (authPort != 0) {
                server.setAuthPort(authPort);
            }
        }
        return server;
    }

}
