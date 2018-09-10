package com.deathwishsoftware.oauth2radius.radius;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
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
