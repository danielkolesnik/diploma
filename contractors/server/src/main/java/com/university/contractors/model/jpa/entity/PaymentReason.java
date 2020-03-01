package com.university.contractors.model.jpa.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Payment Reason
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "payment_reason")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "paymentReasonName"})
@EqualsAndHashCode(of = {"id"})
public class PaymentReason implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_reason")
    private Long id;

    @Column(name = "payment_reason_name")
    private String paymentReasonName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_payment_type")
    private PaymentType paymentType;
}
