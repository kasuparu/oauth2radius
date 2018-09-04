package com.deathwishsoftware.oauth2radius;

import org.tinyradius.packet.AccessRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusClient;

public class RadiusIntegrationTestBase {

    protected RadiusClient client;

    RadiusIntegrationTestBase() {
        // TODO Get params from test profile config
        this.client = new RadiusClient("localhost", "shared-secret");
        // TODO Get server port from application config
        this.client.setAuthPort(30000);
    }

    protected RadiusPacket makeRequest(String userName, String userPassword) throws Exception {
        AccessRequest request = new AccessRequest(userName, userPassword);
        request.setAuthProtocol(AccessRequest.AUTH_CHAP);
        return this.client.authenticate(request);
    }

}
