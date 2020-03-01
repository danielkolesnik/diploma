package com.university.contractors.model.jpa.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Deduct Reason Entity Definition
 *
 * @author   Daniel A. Kolesnik
 * @version  0.1.1
 */
@Entity
@Table(name = "dedact_reason")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "reasonName"})
@EqualsAndHashCode(of = {"id"})
public class DeductReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reason", unique = true, nullable = false)
    private Long id;

    @Column(name = "reason_name")
    private String reasonName;
}
