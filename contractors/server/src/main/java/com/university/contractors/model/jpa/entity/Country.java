package com.university.contractors.model.jpa.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Country
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "country")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "countryNameEng"})
@EqualsAndHashCode(of = {"id"})
public class Country implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country")
    private Long id;

    @Column(name = "country_name_ua")
    private String countryNameUa;

    @Column(name = "country_name_eng")
    private String countryNameEng;

    @Column(name = "country_name_ru")
    private String countryNameRu;
}
