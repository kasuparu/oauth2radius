package com.deathwishsoftware.oauth2radius.fixtures;

import com.deathwishsoftware.oauth2radius.persistence.RadCheck;
import com.deathwishsoftware.oauth2radius.persistence.RadCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Fixtures {

    @Autowired
    private RadCheckRepository radCheckRepository;

    public void insertBasicUsers() {
        RadCheck l2tp = new RadCheck(1, "l2tp", "Cleartext-Password", ":=", "l2tp");
        // TODO crypt the password and use Crypt-Password
        RadCheck test = new RadCheck(2, "testuser@mailinator.com", "Cleartext-Password", ":=", "testuser@mailinator.com-password");
        this.radCheckRepository.save(l2tp);
        this.radCheckRepository.save(test);
    }

}
