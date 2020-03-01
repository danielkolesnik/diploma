package com.university.contractors.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    ADMIN("ADMIN"), USER("USER");

    private final String value;
}
