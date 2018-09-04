package com.deathwishsoftware.oauth2radius;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tinyradius.packet.RadiusPacket;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RadiusEndToEndIT extends RadiusIntegrationTestBase {

    @Test
    public void serverRespondsSuccess() throws Exception {
        RadiusPacket response = this.makeRequest("testuser@mailinator.com", "testuser@mailinator.com-password");
        Assert.assertEquals(RadiusPacket.ACCESS_ACCEPT, response.getPacketType());
    }

    @Test
    public void serverRespondsFailure() throws Exception {
        RadiusPacket response = this.makeRequest("testuser@mailinator.com", "testuser@mailinator.com-wrongpassword");
        Assert.assertEquals(RadiusPacket.ACCESS_REJECT, response.getPacketType());
    }

}
