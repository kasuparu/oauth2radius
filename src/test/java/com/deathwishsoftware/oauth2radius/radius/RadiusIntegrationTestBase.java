package com.deathwishsoftware.oauth2radius.radius;

import com.deathwishsoftware.oauth2radius.fixtures.Fixtures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.tinyradius.packet.AccessRequest;
import org.tinyradius.packet.AccountingRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusClient;

@ActiveProfiles("test")
public class RadiusIntegrationTestBase {

    @Autowired
    protected RadiusClient client;

    @Autowired
    protected Fixtures fixtures;

    RadiusIntegrationTestBase() {
    }

    protected RadiusPacket makeAuthenticationRequest(String userName, String userPassword) throws Exception {
        AccessRequest request = new AccessRequest(userName, userPassword);
        request.setAuthProtocol(AccessRequest.AUTH_CHAP);
        return this.client.authenticate(request);
    }

    protected RadiusPacket makeAccountingStartRequest(String userName) throws Exception {
        AccountingRequest request = new AccountingRequest(userName, AccountingRequest.ACCT_STATUS_TYPE_START);
        return this.client.account(request);
    }

}
