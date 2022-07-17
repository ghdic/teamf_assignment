package com.timf.teamfreash.repository;

import com.timf.teamfreash.model.DeliveryDriver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryDriverRepo extends CrudRepository<DeliveryDriver, Long> {
    Optional<DeliveryDriver> findById(long id);
}
