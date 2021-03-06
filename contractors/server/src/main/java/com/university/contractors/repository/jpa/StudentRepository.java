package com.university.contractors.repository.jpa;

import com.university.contractors.model.jpa.entity.Student;
import com.university.contractors.repository.jpa.core.CoreRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CoreRepository<Student, Long> {

    Optional<Student> findById(Long itemId);



}
