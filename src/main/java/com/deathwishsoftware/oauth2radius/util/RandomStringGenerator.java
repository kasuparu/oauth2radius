package com.deathwishsoftware.oauth2radius.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringGenerator {

    public static String randomAlphanumeric() {
        return RandomStringUtils.randomAlphanumeric(20);
    }

}
