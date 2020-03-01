package com.university.contractors.model.jpa.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString(of = {"value"})
public enum ReportFormat {

    HTML("HTML"), XLS("XLS");

    private String value;
}
