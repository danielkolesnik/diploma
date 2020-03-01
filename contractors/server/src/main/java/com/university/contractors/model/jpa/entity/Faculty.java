package com.university.contractors.model.jpa.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Faculty
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "faculty")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "facultyName"})
@EqualsAndHashCode(of = {"id"})
public class Faculty implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_faculty")
    private Long id;

    @Column(name = "faculty_name")
    private String facultyName;

    @Column(name = "faculty_name_short")
    private String facultyNameShort;

    @Column(name = "faculty_name_eng")
    private String facultyNameEng;

    @Column(name = "faculty_name_ru")
    private String facultyNameRu;
}
