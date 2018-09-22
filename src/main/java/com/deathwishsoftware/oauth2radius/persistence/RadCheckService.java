package com.deathwishsoftware.oauth2radius.persistence;

import com.deathwishsoftware.oauth2radius.util.RandomStringGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RadCheckService {

    private static Logger logger = Logger.getLogger(RadCheckService.class);

    @Autowired
    private RadCheckRepository radCheckRepository;

    public String getUserPassword(String userName) {
        RadCheck userRecord = this.radCheckRepository.findByUsername(userName);
        if (userRecord == null) {
            return null;
        }
        if ("Cleartext-Password".equals(userRecord.getAttribute()) && ":=".equals(userRecord.getOp())) {
            return userRecord.getValue();
        }
        logger.warn(String.format("Password for user %s must be stored in cleartext", userName));
        return null;
    }

    @Transactional
    public RadCheck generateUserPasswordIfNeeded(String userName) {
        RadCheck userRecord = this.radCheckRepository.findByUsername(userName);
        if (userRecord != null) {
            return userRecord;
        }

        String password = RandomStringGenerator.randomAlphanumeric();
        logger.info("Generated password for " + userName + ": " + password);

        RadCheck newUserRecord = new RadCheck(null, userName, "Cleartext-Password", ":=", password);
        this.radCheckRepository.save(newUserRecord);
        return newUserRecord;
    }

}
