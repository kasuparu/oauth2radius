package com.deathwishsoftware.oauth2radius.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Profile("test")
public class RadiusServerRunner {

    @Autowired
    private RadiusServerImpl server;

    @PostConstruct
    public void start() {
        server.start(true, true);
    }

    @PreDestroy
    public void stop() {
        server.stop();
    }

}
