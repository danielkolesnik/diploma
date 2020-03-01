package com.university.contractors.model;

import com.university.contractors.model.jpa.entity.EducationProgram;

public final class EducationProgramBuilder {
    private EducationProgram educationProgram;

    private EducationProgramBuilder() {
        educationProgram = new EducationProgram();
    }

    public static EducationProgramBuilder anEducationProgram() {
        return new EducationProgramBuilder();
    }

    public EducationProgramBuilder id(Long id) {
        educationProgram.setId(id);
        return this;
    }

    public EducationProgramBuilder educProgName(String educProgName) {
        educationProgram.setEducProgName(educProgName);
        return this;
    }

    public EducationProgram build() {
        return educationProgram;
    }
}
