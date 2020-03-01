package com.university.contractors.model.data;

import com.university.contractors.model.jpa.entity.*;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO: refactor into StudentSearchDTO
 */
@Getter
@Setter
public class SearchStudentResult {

    private Student student;

    private Contract contract;

    private Country country;

    private Integer course;

    private EducationForm educationForm;

    private Faculty faculty;

    private Direction direction;

    private EducationLevel educationLevel;

    // Added for compatibility with Hibernate
    public SearchStudentResult(Contract contract) {
        this.student = contract.getStudent();
        this.contract = contract;
        this.country = student.getCountry();
        this.course = contract.getCourse();
        this.educationForm = contract.getEducationForm();
        this.faculty = contract.getDirection().getFaculty();
        this.direction = contract.getDirection();
        this.educationLevel = contract.getEducationLevel();
    }

}
