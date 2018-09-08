package com.deathwishsoftware.oauth2radius.persistence;

import org.springframework.data.repository.CrudRepository;

public interface RadCheckRepository extends CrudRepository<RadCheck, Integer> {

    RadCheck findByUsername(String username);

}
