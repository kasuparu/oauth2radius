package com.deathwishsoftware.oauth2radius.config;

import com.deathwishsoftware.oauth2radius.persistence.RadCheckService;
import com.deathwishsoftware.oauth2radius.util.AuthenticationProperties;
import com.deathwishsoftware.oauth2radius.util.OAuth2AuthenticationUtils;
import com.deathwishsoftware.oauth2radius.util.WhitelistedDomainsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Configuration
public class AuthenticationListener {

    @Autowired
    private RadCheckService radCheckService;

    @Autowired
    private WhitelistedDomainsUtils whitelistedDomainsUtils;

    @EventListener
    public void authSuccessEventListener(AuthenticationSuccessEvent authorizedEvent) {
        Authentication auth = authorizedEvent.getAuthentication();
        if (!(auth instanceof OAuth2Authentication)) {
            return;
        }

        AuthenticationProperties properties = OAuth2AuthenticationUtils.extractAuthenticationProperties((OAuth2Authentication)auth);
        String email = properties.getEmail();
        if (whitelistedDomainsUtils.isAllowed(email)) {
            this.radCheckService.generateUserPasswordIfNeeded(email);
        }
    }

}
