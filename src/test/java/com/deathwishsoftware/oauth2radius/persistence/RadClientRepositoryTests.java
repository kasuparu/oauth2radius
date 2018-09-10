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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RadClientRepositoryTests {

    @Autowired
    private RadClientRepository radClientRepository;

    @Autowired
    private Fixtures fixtures;

	@Before
	public void setUp() {
        fixtures.insertLocalhostClient();
	}

	@Test
	public void findAllTest() {
	    Iterable<RadClient> clientIterable = radClientRepository.findAll();
        List<RadClient> clients = new ArrayList<>();
        for (RadClient client : clientIterable) {
            clients.add(client);
        }
	    Assert.assertEquals(1, clients.size());
        RadClient client = clients.get(0);
        Assert.assertEquals("127.0.0.1", client.getInetAddress());
	}

}
