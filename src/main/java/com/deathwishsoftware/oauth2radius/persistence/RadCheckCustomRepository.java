package com.deathwishsoftware.oauth2radius.persistence;

public interface RadCheckCustomRepository {

    RadCheck findToAuthenticate(String username, String password);

}
