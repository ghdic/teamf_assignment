package com.timf.teamfreash.repository;

import com.timf.teamfreash.model.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepo extends CrudRepository<Provider, Long> {
    Optional<Provider> findByUserId(String userId);
}
