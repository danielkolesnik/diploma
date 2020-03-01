package com.university.contractors.model.jpa.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Direction
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "direction")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "directionName"})
@EqualsAndHashCode(of = {"id"})
public class Direction implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direction")
    private Long id;

    @Column(name = "direction_name")
    private String directionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_faculty")
    private Faculty faculty;
}
