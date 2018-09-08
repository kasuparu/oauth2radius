package com.deathwishsoftware.oauth2radius.fixtures;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixturesConfig {

    @Bean
    public Fixtures getFixtures() {
        return new Fixtures();
    }
}
