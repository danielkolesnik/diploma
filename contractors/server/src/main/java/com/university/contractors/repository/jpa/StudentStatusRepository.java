package com.university.contractors.repository.jpa;

import com.university.contractors.model.jpa.entity.StudentStatus;
import org.springframework.data.repository.CrudRepository;

public interface StudentStatusRepository extends CrudRepository<StudentStatus, Long> {

}
