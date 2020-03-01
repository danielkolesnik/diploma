package com.university.contractors.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Order
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "orderNumber"})
@EqualsAndHashCode(of = {"id"})
public class Order implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "order_date")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date orderDate;

    @Column(name = "related_date")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date relatedDate;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_type_order")
    private OrderType orderType;

}
