package com.university.contractors.model.jpa.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString(of = {"value"})
public enum ReportFieldType {

    TEXT("TEXT"), DROPDOWN("DROPDOWN"), DATE("DATE");

    private String value;
}
