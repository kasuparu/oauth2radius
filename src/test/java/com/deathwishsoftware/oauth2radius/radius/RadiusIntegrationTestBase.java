package com.deathwishsoftware.oauth2radius.radius;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.tinyradius.packet.AccessRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusClient;

@ActiveProfiles("test")
public class RadiusIntegrationTestBase {

    @Autowired
    protected RadiusClient client;

    RadiusIntegrationTestBase() {
    }

    protected RadiusPacket makeRequest(String userName, String userPassword) throws Exception {
        AccessRequest request = new AccessRequest(userName, userPassword);
        request.setAuthProtocol(AccessRequest.AUTH_CHAP);
        return this.client.authenticate(request);
    }

}
