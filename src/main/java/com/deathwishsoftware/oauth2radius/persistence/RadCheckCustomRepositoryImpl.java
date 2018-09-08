package com.deathwishsoftware.oauth2radius.persistence;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
public class RadCheckCustomRepositoryImpl implements RadCheckCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    public RadCheck findToAuthenticate(String username, String password) {
        Query query = entityManager.createNativeQuery("SELECT * FROM radcheck " +
                "WHERE username = ? AND (attribute = ? OR attribute = ?) AND op = ? AND value = ?", RadCheck.class);
        query.setParameter(1, username);
        query.setParameter(2, "Cleartext-Password");
        query.setParameter(3, "Crypt-Password");
        query.setParameter(4, ":=");
        query.setParameter(5, password);

        RadCheck authenticatedUser = null;

        try {
            authenticatedUser = (RadCheck)query.getSingleResult();
        } catch (NoResultException e) {
            // This is normal
        }

        return authenticatedUser;
    }

}
