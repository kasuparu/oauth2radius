package com.deathwishsoftware.oauth2radius.radius;

import com.deathwishsoftware.oauth2radius.fixtures.Fixtures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.tinyradius.packet.AccessRequest;
import org.tinyradius.packet.AccountingRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusClient;
import org.tinyradius.util.RadiusException;

import java.io.IOException;

@ActiveProfiles("test")
public class RadiusIntegrationTestBase {

    @Autowired
    protected RadiusClient client;

    @Autowired
    protected Fixtures fixtures;

    RadiusIntegrationTestBase() {
    }

    protected RadiusPacket makeChapAuthenticationRequest(String userName, String userPassword) throws RadiusException, IOException {
        AccessRequest request = new AccessRequest(userName, userPassword);
        request.setAuthProtocol(AccessRequest.AUTH_CHAP);
        return this.client.authenticate(request);
    }

    protected void makeMalformedChapAuthenticationRequest(String userName) throws RadiusException, IOException {
        AccessRequest request = new MalformedAccessRequest(userName);
        request.setAuthProtocol(AccessRequest.AUTH_CHAP);
        int originalRetryCount = this.client.getRetryCount();
        this.client.setRetryCount(1);
        try {
            this.client.authenticate(request);
        } catch (IOException e) {
            this.client.setRetryCount(originalRetryCount);
            throw e;
        }
    }

    protected RadiusPacket makeAccountingStartRequest(String userName) throws RadiusException, IOException {
        AccountingRequest request = new AccountingRequest(userName, AccountingRequest.ACCT_STATUS_TYPE_START);
        return this.client.account(request);
    }

}
