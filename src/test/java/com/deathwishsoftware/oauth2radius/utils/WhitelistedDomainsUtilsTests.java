package com.deathwishsoftware.oauth2radius.utils;

import com.deathwishsoftware.oauth2radius.util.WhitelistedDomainsUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WhitelistedDomainsUtilsTests {

    @Test
    public void containsTest() {
        WhitelistedDomainsUtils whitelistedDomainsUtils = new WhitelistedDomainsUtils(new String[]{"Mailinator.com"});
        Assert.assertTrue(whitelistedDomainsUtils.isAllowed("someone@MAILINATOR.COM"));
    }

    @Test
    public void doesNotContainTest() {
        WhitelistedDomainsUtils whitelistedDomainsUtils = new WhitelistedDomainsUtils(new String[]{"Mailinator.com"});
        Assert.assertFalse(whitelistedDomainsUtils.isAllowed("someone@failfish.com"));
    }

    @Test
    public void emptyConfigTest() {
        WhitelistedDomainsUtils whitelistedDomainsUtils = new WhitelistedDomainsUtils(new String[]{});
        Assert.assertFalse(whitelistedDomainsUtils.isAllowed("someone@failfish.com"));
    }

}
