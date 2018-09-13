package com.deathwishsoftware.oauth2radius.fixtures;

import com.deathwishsoftware.oauth2radius.persistence.RadCheck;
import com.deathwishsoftware.oauth2radius.persistence.RadCheckRepository;
import com.deathwishsoftware.oauth2radius.utils.RadiusServerImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class Fixtures {

    @Autowired
    private RadCheckRepository radCheckRepository;

    @Autowired
    private RadiusServerImpl server;

    public void insertBasicUsers() {
        this.radCheckRepository.deleteAll();
        RadCheck l2tp = new RadCheck(1, "l2tp", "Cleartext-Password", ":=", "l2tp");
        // TODO crypt the password and use Crypt-Password
        RadCheck test = new RadCheck(2, "testuser@mailinator.com", "Cleartext-Password", ":=", "testuser@mailinator.com-password");
        this.radCheckRepository.save(l2tp);
        this.radCheckRepository.save(test);
    }

    public void insertBasicClients() {
        Map<String, String> clients = new HashMap<>();
        clients.put("192.168.0.1", "shared-secret");
        clients.put("127.0.0.1", "shared-secret");
        this.server.setClients(clients);
    }

}
