package com.deathwishsoftware.oauth2radius.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RadiusServerConfig {

    @Value("${oauth2radius.auth-port:0}")
    private int authPort;

    @Value("${oauth2radius.acct-port:0}")
    private int acctPort;

    @Bean
    public RadiusServerImpl getRadiusServer() {
        RadiusServerImpl server = new RadiusServerImpl();
        if (authPort > 1 && authPort < 65535) {
            server.setAuthPort(authPort);
        }
        if (acctPort > 1 && acctPort < 65535) {
            server.setAcctPort(acctPort);
        }
        return server;
    }

}
