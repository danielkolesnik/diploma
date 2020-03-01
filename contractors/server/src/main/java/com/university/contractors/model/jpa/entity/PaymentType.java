package com.university.contractors.model.jpa.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Payment Type
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "payment_type")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "paymentTypeName"})
@EqualsAndHashCode(of = {"id"})
public class PaymentType implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_type")
    private Long id;

    @Column(name = "payment_type_name")
    private String paymentTypeName;

}
