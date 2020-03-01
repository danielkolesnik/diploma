package com.university.contractors.model;

import com.university.contractors.model.jpa.entity.Direction;
import com.university.contractors.model.jpa.entity.Faculty;

public final class DirectionBuilder {
    private Direction direction;

    private DirectionBuilder() {
        direction = new Direction();
    }

    public static DirectionBuilder aDirection() {
        return new DirectionBuilder();
    }

    public DirectionBuilder id(Long id) {
        direction.setId(id);
        return this;
    }

    public DirectionBuilder directionName(String directionName) {
        direction.setDirectionName(directionName);
        return this;
    }

    public DirectionBuilder faculty(Faculty faculty) {
        direction.setFaculty(faculty);
        return this;
    }

    public Direction build() {
        return direction;
    }
}
