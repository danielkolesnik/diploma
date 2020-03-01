package com.university.contractors.model.jpa.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Education Language
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "educ_languages")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "educLanguageName"})
@EqualsAndHashCode(of = {"id"})
public class EducationLanguage implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_educ_language")
    private Long id;

    private String educLanguageName;
}
