package com.university.contractors.repository.jpa;

import com.university.contractors.model.jpa.entity.Direction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DirectionRepository extends CrudRepository<Direction, Long> {

    List<Direction> findByFacultyId(Long facultyId);
}
