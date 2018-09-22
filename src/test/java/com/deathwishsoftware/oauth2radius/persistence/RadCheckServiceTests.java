package com.deathwishsoftware.oauth2radius.persistence;

import com.deathwishsoftware.oauth2radius.fixtures.Fixtures;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RadCheckServiceTests {

    @Autowired
    private RadCheckService radCheckService;

    @Autowired
    private Fixtures fixtures;

	@Before
	public void setUp() {
	    fixtures.clearUsers();
        fixtures.insertBasicUsers();
	}

    @Test
	public void getNonExistingUserPasswordTest() {
	    String password = radCheckService.getUserPassword("nonexistinguser@mailinator.com");
	    Assert.assertNull(password);
    }

    @Test
    public void getExistingUserPasswordTest() {
        String password = radCheckService.getUserPassword("testuser@mailinator.com");
        Assert.assertNotNull(password);
        Assert.assertEquals("testuser@mailinator.com-password", password);
    }

    @Test
    public void generatePasswordForExistingUserTest() {
        RadCheck user = radCheckService.generateUserPasswordIfNeeded("testuser@mailinator.com");
        Assert.assertNotNull(user);
        Assert.assertEquals("testuser@mailinator.com-password", user.getValue());
    }

    @Test
    public void generatePasswordForNonExistingUserTest() {
        RadCheck user = radCheckService.generateUserPasswordIfNeeded("nonexistinguser@mailinator.com");
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getValue());
        Assert.assertTrue(user.getValue().matches("^([a-zA-Z0-9]){20}$"));
    }

}
