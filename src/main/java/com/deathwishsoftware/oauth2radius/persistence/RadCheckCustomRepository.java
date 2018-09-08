package com.deathwishsoftware.oauth2radius.persistence;

import java.util.Optional;

public interface RadCheckCustomRepository {

    public RadCheck findToAuthenticate(String username, String password);

}
