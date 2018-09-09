package com.deathwishsoftware.oauth2radius.radius;

import com.deathwishsoftware.oauth2radius.persistence.RadCheck;
import com.deathwishsoftware.oauth2radius.persistence.RadCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.tinyradius.util.RadiusServer;

import java.net.InetSocketAddress;

public class RadiusServerImpl extends RadiusServer {

    @Autowired
    private RadCheckRepository radCheckRepository;

    public RadiusServerImpl() {
    }

    public String getSharedSecret(InetSocketAddress _remoteAddress) {
        // TODO Actually get it from DB
        return "shared-secret";
    }

    public String getUserPassword(String userName) {
        RadCheck userRecord = this.radCheckRepository.findByUsername(userName);
        if (userRecord == null) {
            return null;
        }
        return userRecord.getValue();
    }

}
