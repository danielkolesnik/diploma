package com.university.contractors.model.jpa.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Education Program
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "education_program")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "educProgName"})
@EqualsAndHashCode(of = {"id"})
public class EducationProgram implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_educ_prog")
    private Long id;

    @Column(name = "educ_prog_name")
    private String educProgName;
}
