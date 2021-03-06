package com.deathwishsoftware.oauth2radius.utils;

import com.deathwishsoftware.oauth2radius.persistence.RadCheckService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.tinyradius.util.RadiusServer;

import java.net.InetSocketAddress;
import java.util.Map;

public class RadiusServerImpl extends RadiusServer {

    private static Logger logger = Logger.getLogger(RadiusServerImpl.class);

    @Autowired
    private RadCheckService radCheckService;

    private Map<String, String> clients;

    public RadiusServerImpl() {
    }

    @Override
    public String getSharedSecret(InetSocketAddress remoteAddress) {
        String address = remoteAddress.getAddress().getHostAddress();
        if (clients.containsKey(address)) {
            return clients.get(address);
        }
        return null;
    }

    @Override
    public String getUserPassword(String userName) {
        return this.radCheckService.getUserPassword(userName);
    }

    public void setClients(Map<String, String> clients) {
        this.clients = clients;
    }
}
