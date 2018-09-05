package com.deathwishsoftware.oauth2radius;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.tinyradius.packet.AccessRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusClient;

@ActiveProfiles("test")
public abstract class RadiusIntegrationTestBase {

    protected RadiusClient client;

    @Value("${application.authPort:0}")
    protected String authPortStr;

    RadiusIntegrationTestBase() {
        // TODO Get params from test profile config
        this.client = new RadiusClient("localhost", "shared-secret");

        int authPort = 0;
        try {
            authPort = Integer.parseInt(authPortStr);
        } catch (Exception e) {
            // TODO Log an error
        } finally {
            if (authPort != 0) {
                this.client.setAuthPort(authPort);
            }
        }
    }

    protected RadiusPacket makeRequest(String userName, String userPassword) throws Exception {
        AccessRequest request = new AccessRequest(userName, userPassword);
        request.setAuthProtocol(AccessRequest.AUTH_CHAP);
        return this.client.authenticate(request);
    }

}
