package com.university.contractors.controller.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.university.contractors.model.jpa.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchStudent {

    private String surname;

    private String name;

    private Date dataOfBirth;

    private Nationality nationality;

    private Country country;

    private String contractNumber;

    private Integer course;

    private Faculty faculty;

    private Direction direction;

    private EducationProgram educationProgram;

    private EducationLevel educationLevel;

    private EducationForm educationForm;

    private ArrivalLine arrivalLine;

    private EducationLanguage educationLanguage;

    @JsonIgnore
    private Boolean isBudget;

    @JsonIgnore
    private Boolean isActive;

}
