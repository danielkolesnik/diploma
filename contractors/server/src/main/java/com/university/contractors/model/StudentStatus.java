package com.university.contractors.model;

import com.google.common.base.Objects;
import lombok.*;

import javax.persistence.*;

/**
 * Student Status
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "student_status")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "studentStatusName"})
@EqualsAndHashCode(of = {"id"})
public class StudentStatus implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student_status")
    private Long id;

    @Column(name = "student_status_name")
    private String studentStatusName;
}
