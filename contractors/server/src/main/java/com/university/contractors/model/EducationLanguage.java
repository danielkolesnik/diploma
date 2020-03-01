package com.university.contractors.model;

import com.google.common.base.Objects;
import lombok.*;

import javax.persistence.*;

/**
 * Education Language
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity(name = "educ_languages")
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
