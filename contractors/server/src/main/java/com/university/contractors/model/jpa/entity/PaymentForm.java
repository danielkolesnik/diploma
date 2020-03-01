package com.university.contractors.model.jpa.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Payment Form
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "payment_form")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "paymentFormName"})
@EqualsAndHashCode(of = {"id"})
public class PaymentForm implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_form")
    private Long id;

    @Column(name = "payment_form_name")
    private String paymentFormName;

}
