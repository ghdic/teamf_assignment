package com.timf.teamfreash.repository;

import com.timf.teamfreash.model.Penalty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenaltyRepo extends CrudRepository<Penalty, Long> {
}
