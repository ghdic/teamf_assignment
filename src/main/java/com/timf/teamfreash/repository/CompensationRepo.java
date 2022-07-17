package com.timf.teamfreash.repository;

import com.timf.teamfreash.model.Compensation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompensationRepo extends CrudRepository<Compensation, Long> {
    List<Compensation> findAll();
    List<Compensation> findByVoc_DefendantId(long voc_defendantId);
}
