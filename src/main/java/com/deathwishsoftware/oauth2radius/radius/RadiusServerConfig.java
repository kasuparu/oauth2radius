package com.deathwishsoftware.oauth2radius.radius;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

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
        // TODO Do I break DI here?
        Map<String, String> clients = getRadiusServerClientRefresher().getClients();
        server.setClients(clients);
        return server;
    }

    @Bean
    public RadiusServerClientRefresher getRadiusServerClientRefresher() {
        return new RadiusServerClientRefresher();
    }

}
