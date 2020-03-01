package com.university.contractors.model.data;

import com.university.contractors.model.jpa.entity.Contract;
import com.university.contractors.model.jpa.entity.Order;
import com.university.contractors.model.jpa.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCard {

    private Student student;

    private Contract currentContract;

    private List<Order> orders;

    private List<Contract> contracts;
}
