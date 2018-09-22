package com.deathwishsoftware.oauth2radius.persistence;

import com.deathwishsoftware.oauth2radius.fixtures.Fixtures;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RadCheckRepositoryTests {

    @Autowired
    private RadCheckRepository radCheckRepository;

    @Autowired
    private Fixtures fixtures;

	@Before
	public void setUp() {
	    fixtures.clearUsers();
        fixtures.insertBasicUsers();
	}

	@Test
	public void findAllTest() {
	    Iterable<RadCheck> userIterable = radCheckRepository.findAll();
        List<RadCheck> users = new ArrayList<>();
        for (RadCheck user : userIterable) {
            users.add(user);
        }
	    Assert.assertEquals(2, users.size());
	}

	@Test
	public void getByUsernameTest() {
        RadCheck test = radCheckRepository.findByUsername("testuser@mailinator.com");
        Assert.assertNotNull(test);
        Assert.assertEquals("testuser@mailinator.com-password", test.getValue());
    }

}
