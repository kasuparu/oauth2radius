package com.deathwishsoftware.oauth2radius.radius;

import com.deathwishsoftware.oauth2radius.persistence.RadCheck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tinyradius.packet.RadiusPacket;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RadiusNewUserIntegrationTests extends RadiusIntegrationTestBase {

    private RadCheck newUser;

    // TODO Make the set-up a static @BeforeClass
    // This require to figure out how to make everything underlying static
    // This will allow to not delete all rows on insertion
    @Before
    public void setUp() {
        fixtures.clearUsers();
        fixtures.insertBasicClients();
        newUser = radCheckService.generateUserPasswordIfNeeded("newuser@mailinator.com");
    }

    @Test
    public void serverRespondsChapSuccess() throws Exception {
        RadiusPacket response = this.makeChapAuthenticationRequest("newuser@mailinator.com", newUser.getValue());
        Assert.assertEquals(RadiusPacket.ACCESS_ACCEPT, response.getPacketType());
    }

}
