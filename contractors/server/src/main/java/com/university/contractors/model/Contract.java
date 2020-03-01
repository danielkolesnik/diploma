package com.university.contractors.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Objects;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Country
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "contract")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "contractNumber"})
@EqualsAndHashCode(of = {"id"})
public class Contract implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contract")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_student")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_contract_type")
    private ContractType contractType;

    @Column(name = "contract_date")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date contractDate;

    @Column(name = "contract_value")
    private Float contractValue;

    @Column(name = "contract_number")
    private String contractNumber;

    @ManyToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "contract_order",
        joinColumns = {@JoinColumn(name = "ref_contract")},
        inverseJoinColumns = {@JoinColumn(name = "ref_order")}
    )
    private Set<Order> orders = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_dedact_order_project")
    private Order deactOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_educ_prog")
    private EducationProgram educationProgram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_educ_level")
    private EducationLevel educationLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_educ_form")
    private EducationForm educationForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_arrival_line")
    private ArrivalLine arrivalLine;

    @Column(name = "payer")
    private String payer;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "date_in")
    private Date dateIn;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "plan_date_out")
    private Date planDateOut;

    @Column(name = "course")
    private Integer course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_educ_language")
    private EducationLanguage educationLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_student_status")
    private StudentStatus studentStatus;

    @Column(name = "years_of_educ")
    private Float yearsOfEduc;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_usual_payment_form")
    private PaymentForm usualPaymentForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_direction")
    private Direction direction;

    @Column(name = "is_budget")
    private Boolean isBudget;

    @Column(name = "is_active")
    private Boolean isActive;
}
