package com.timf.teamfreash.repository;

import com.timf.teamfreash.model.Complaint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepo extends CrudRepository<Complaint, Long> {
}
