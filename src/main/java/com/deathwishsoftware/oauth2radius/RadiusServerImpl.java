package com.deathwishsoftware.oauth2radius;

import org.tinyradius.util.RadiusServer;

import java.net.InetSocketAddress;

public class RadiusServerImpl extends RadiusServer {

    public RadiusServerImpl() {
    }

    public String getSharedSecret(InetSocketAddress _remoteAddress) {
        // TODO Actually get it from application profile config or DB
        return "shared-secret";
    }

    public String getUserPassword(String userName) {
        // TODO Actually get it from DB
        return userName + "-password";
    }

}
