package com.deathwishsoftware.oauth2radius.radius;

import org.tinyradius.packet.AccessRequest;

public class MalformedAccessRequest extends AccessRequest {

    public MalformedAccessRequest(String userName) { super(userName, "willberemoved"); }

    @Override
    protected void encodeRequestAttributes(String sharedSecret) {
        super.encodeRequestAttributes(sharedSecret);
        removeAttributes(2/* USER_PASSWORD */);
        removeAttributes(3/* CHAP_PASSWORD */);
    }

}
