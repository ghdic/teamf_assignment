package com.timf.teamfreash.repository;

import com.timf.teamfreash.model.VOC;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VOCRepo extends CrudRepository<VOC, Long> {
    List<VOC> findAll();
}
