package com.deathwishsoftware.oauth2radius.radius;

import com.deathwishsoftware.oauth2radius.persistence.RadClient;
import com.deathwishsoftware.oauth2radius.persistence.RadClientRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

// TODO Move this as a method of server without breaking DI
public class RadiusServerClientRefresher {

    private static Logger logger = Logger.getLogger(RadiusServerClientRefresher.class);

    @Autowired
    private RadClientRepository radClientRepository;

    public Map<String, String> getClients() {
        logger.info("Fetching clients...");

        Iterable<RadClient> clientsIterable = radClientRepository.findAll();
        Map<String, String> clients = new HashMap<>();

        for (RadClient client : clientsIterable) {
            logger.info("Adding client" + client.toString());
            clients.put(client.getInetAddress(), client.getSharedSecret());
        }
        logger.info("Total clients: " + clients.size());

        return clients;
    }
}
