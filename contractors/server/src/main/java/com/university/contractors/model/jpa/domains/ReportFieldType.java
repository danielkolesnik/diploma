package com.university.contractors.model.jpa.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString(of = {"value"})
public enum ReportFieldType {

    TEXT("TEXT"),

    DROPDOWN("DROPDOWN"),

    DATE("DATE");

    private String value;

    /**
     * Get proper ReportFieldType from String
     *
     * @param value
     * @return ReportFieldType
     */
    public static ReportFieldType of(String value) {
        ReportFieldType result = null;

        ReportFieldType tmpValue = ReportFieldType.valueOf(value);
        if (tmpValue != null) {
            result = tmpValue;
        }

        return result;
    }
}
