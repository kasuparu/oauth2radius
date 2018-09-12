package com.deathwishsoftware.oauth2radius.radius;

import org.apache.log4j.Logger;
import org.tinyradius.util.RadiusException;

public class AccessRequest extends org.tinyradius.packet.AccessRequest {

    private static Logger logger = Logger.getLogger(AccessRequest.class);

    // TODO Unused, as it's impossible to override the static RadiusPacket.createRadiusPacket() without reflection
    @Override
    protected void decodeRequestAttributes(String sharedSecret)
            throws RadiusException {
        try {
            super.decodeRequestAttributes(sharedSecret);
        } catch (RadiusException e) {
            logger.info("Malformed AccessRequest attributes: " + this.toString());
            throw e;
        }
    }

}
