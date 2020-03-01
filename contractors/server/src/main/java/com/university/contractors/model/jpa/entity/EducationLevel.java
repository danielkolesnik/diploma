package com.university.contractors.model.jpa.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Education Level
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "education_level")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "educLevelName"})
@EqualsAndHashCode(of = {"id"})
public class EducationLevel implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_educ_level")
    private Long id;

    @Column(name = "educ_level_name")
    private String educLevelName;

    @Column(name = "number_of_month")
    private Integer numberOfMonth;

    @Column(name = "is_summer_month")
    private Boolean isSummerMonth;
}
