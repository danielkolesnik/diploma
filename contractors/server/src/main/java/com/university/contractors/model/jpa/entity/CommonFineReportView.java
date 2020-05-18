package com.university.contractors.model.jpa.entity;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Common Student Payment Report View Entity Definition
 *
 * @author    Daniel A. Kolesnik <daneelkolesnik@gmail.com>
 * @version   0.1.0
 * @since     2020-03-14
 */
@Entity
@Immutable
@Table(name = "common_fine_report")
@Subselect("select uuid() as id, cfr.* from common_fine_report cfr")
public class CommonFineReportView {

    @Id
    private String id;

    @Column(name = "idContract")
    private Long idContract;

    @Column(name = "payFineGrn")
    private BigDecimal payFineGrn;

    @Column(name = "payFineUsd")
    private BigDecimal payFineUsd;

    @Column(name = "addFineGrn")
    private BigDecimal addFineGrn;

    @Column(name = "addFineUsd")
    private BigDecimal addFineUsd;

    @Column(name = "dolgFrn")
    private BigDecimal dolgFrn;

    @Column(name = "dolgUsd")
    private BigDecimal dolgUsd;

}
