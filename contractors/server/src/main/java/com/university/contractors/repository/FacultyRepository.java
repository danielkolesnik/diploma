package com.university.contractors.repository;

import com.university.contractors.model.jpa.entity.Faculty;
import org.springframework.data.repository.CrudRepository;

public interface FacultyRepository extends CrudRepository<Faculty, Long> {
}
