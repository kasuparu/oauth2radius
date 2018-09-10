package com.deathwishsoftware.oauth2radius.radius;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tinyradius.packet.RadiusPacket;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RadiusEndToEndIntegrationTests extends RadiusIntegrationTestBase {

    // TODO Make the set-up a static @BeforeClass
    // This require to figure out how to make everything underlying static
    // This will allow to not delete all rows on insertion
    @Before
    public void setUp() {
        fixtures.insertBasicUsers();
        fixtures.insertLocalhostClient();
        fixtures.refreshClients();
    }

    @Test
    public void serverRespondsSuccess() throws Exception {
        RadiusPacket response = this.makeAuthenticationRequest("testuser@mailinator.com", "testuser@mailinator.com-password");
        Assert.assertEquals(RadiusPacket.ACCESS_ACCEPT, response.getPacketType());
    }

    // TODO Try running several requests in parallel

    @Test
    public void serverRespondsFailure() throws Exception {
        RadiusPacket response = this.makeAuthenticationRequest("testuser@mailinator.com", "testuser@mailinator.com-wrongpassword");
        Assert.assertEquals(RadiusPacket.ACCESS_REJECT, response.getPacketType());
    }

    @Test
    public void serverAccountingStart() throws Exception {
        RadiusPacket response = this.makeAccountingStartRequest("testuser@mailinator.com");
        Assert.assertEquals(RadiusPacket.ACCOUNTING_RESPONSE, response.getPacketType());
    }

    // TODO Check authenticating by crypt-password

}
