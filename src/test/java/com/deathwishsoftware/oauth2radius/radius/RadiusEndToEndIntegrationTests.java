package com.deathwishsoftware.oauth2radius.radius;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tinyradius.packet.RadiusPacket;
import java.net.SocketTimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RadiusEndToEndIntegrationTests extends RadiusIntegrationTestBase {

    // TODO Make the set-up a static @BeforeClass
    // This require to figure out how to make everything underlying static
    // This will allow to not delete all rows on insertion
    @Before
    public void setUp() {
        fixtures.insertBasicUsers();
        fixtures.insertBasicClients();
    }

    @Test
    public void serverRespondsChapSuccess() throws Exception {
        RadiusPacket response = this.makeChapAuthenticationRequest("testuser@mailinator.com", "testuser@mailinator.com-password");
        Assert.assertEquals(RadiusPacket.ACCESS_ACCEPT, response.getPacketType());
    }

    @Test
    public void serverRespondsChapFailure() throws Exception {
        RadiusPacket response = this.makeChapAuthenticationRequest("testuser@mailinator.com", "testuser@mailinator.com-wrongpassword");
        Assert.assertEquals(RadiusPacket.ACCESS_REJECT, response.getPacketType());
    }

    @Test(expected = SocketTimeoutException.class)
    public void serverDoesNotRespondToMalformedPacket() throws Exception {
        this.makeMalformedChapAuthenticationRequest("testuser@mailinator.com");
    }

    @Test
    public void serverAccountingStart() throws Exception {
        RadiusPacket response = this.makeAccountingStartRequest("testuser@mailinator.com");
        Assert.assertEquals(RadiusPacket.ACCOUNTING_RESPONSE, response.getPacketType());
    }

    // TODO Check authenticating by crypt-password

}
