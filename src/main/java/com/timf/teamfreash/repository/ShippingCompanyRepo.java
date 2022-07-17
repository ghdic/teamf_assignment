package com.timf.teamfreash.repository;

import com.timf.teamfreash.model.ShippingCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingCompanyRepo extends CrudRepository<ShippingCompany, Long> {
    Optional<ShippingCompany> findById(long id);
    Optional<ShippingCompany> findByCompanyName(String companyName);
}
