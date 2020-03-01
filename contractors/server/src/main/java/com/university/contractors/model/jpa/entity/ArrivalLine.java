package com.university.contractors.model.jpa.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Arrival Line
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "arrival_line")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "arrivalCenter", "arrivalCenterName"})
@EqualsAndHashCode(of = {"id"})
public class ArrivalLine implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arrival_line")
    private Long id;

    @Column(name = "arrival_center")
    private String arrivalCenter;

    @Column(name = "arrival_center_name")
    private String arrivalCenterName;

}
