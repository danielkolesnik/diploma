package com.university.contractors.repository.jpa;

import com.university.contractors.model.jpa.entity.ArrivalLine;
import org.springframework.data.repository.CrudRepository;

public interface ArrivalLineRepository extends CrudRepository<ArrivalLine, Long> {
}
