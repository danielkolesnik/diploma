package com.university.contractors.model;

import lombok.*;

import javax.persistence.*;

/**
 * Education Form
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "education_form")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "educFormName"})
@EqualsAndHashCode(of = {"id", "educFormName"})
public class EducationForm implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_educ_form")
    private Long id;

    @Column(name = "educ_form_name")
    private String educFormName;
}
