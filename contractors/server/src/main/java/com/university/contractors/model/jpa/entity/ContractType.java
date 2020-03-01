package com.university.contractors.model.jpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contract Type
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "contract_type")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "contractTypeName"})
@EqualsAndHashCode(of = {"id"})
public class ContractType implements IdEntity<Long> {

    @Id
    @Column(name = "id_contract_type")
    private Long id;

    @Column(name = "contract_type_name")
    private String contractTypeName;

}
