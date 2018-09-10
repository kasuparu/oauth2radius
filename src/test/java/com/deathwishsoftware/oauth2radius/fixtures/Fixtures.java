package com.deathwishsoftware.oauth2radius.fixtures;

import com.deathwishsoftware.oauth2radius.persistence.RadCheck;
import com.deathwishsoftware.oauth2radius.persistence.RadCheckRepository;
import com.deathwishsoftware.oauth2radius.persistence.RadClient;
import com.deathwishsoftware.oauth2radius.persistence.RadClientRepository;
import com.deathwishsoftware.oauth2radius.radius.RadiusServerClientRefresher;
import com.deathwishsoftware.oauth2radius.radius.RadiusServerImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class Fixtures {

    @Autowired
    private RadCheckRepository radCheckRepository;

    @Autowired
    private RadClientRepository radClientRepository;

    @Autowired
    private RadiusServerImpl server;

    @Autowired
    private RadiusServerClientRefresher refresher;

    public void insertBasicUsers() {
        this.radCheckRepository.deleteAll();
        RadCheck l2tp = new RadCheck(1, "l2tp", "Cleartext-Password", ":=", "l2tp");
        // TODO crypt the password and use Crypt-Password
        RadCheck test = new RadCheck(2, "testuser@mailinator.com", "Cleartext-Password", ":=", "testuser@mailinator.com-password");
        this.radCheckRepository.save(l2tp);
        this.radCheckRepository.save(test);
    }

    public void insertLocalhostClient() {
        this.radClientRepository.deleteAll();
        RadClient local = new RadClient("127.0.0.1", "shared-secret");
        this.radClientRepository.save(local);
    }

    // TODO This is far from elegant
    public void refreshClients() {
        this.server.setClients(this.refresher.getClients());
    }

}
