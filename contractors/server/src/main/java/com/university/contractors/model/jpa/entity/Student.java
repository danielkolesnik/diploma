package com.university.contractors.model.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Country
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "student")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "name", "surname"})
@EqualsAndHashCode(of = {"id"})
public class Student implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private Long id;

    @Column(name = "stud_surname")
    private String surname;

    @Column(name = "stud_name")
    private String name;

    @Column(name = "stud_middlename")
    private String middleName;

    @Column(name = "date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_country")
    private Country country;

}
