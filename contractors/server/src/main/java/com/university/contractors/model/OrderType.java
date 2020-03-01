package com.university.contractors.model;

import com.google.common.base.Objects;
import lombok.*;

import javax.persistence.*;

/**
 * Order Type
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "types_orders")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "typeOrderName"})
@EqualsAndHashCode(of = {"id"})
public class OrderType implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_order")
    private Long id;

    @Column(name = "type_order_name")
    private String typeOrderName;
}
