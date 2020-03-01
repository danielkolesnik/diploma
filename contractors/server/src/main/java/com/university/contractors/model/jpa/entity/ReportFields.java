package com.university.contractors.model.jpa.entity;

import com.university.contractors.model.jpa.domains.ReportFieldType;
import lombok.*;

import javax.persistence.*;

/**
 * Report Fields Entity Definition
 *
 * @author    Daniel A. Kolesnik <daneelkolesnik@gmail.com>
 * @version   0.1.1
 * @since     2020-03-01
 */
@Entity
@Table(name = "report_fields")
@NoArgsConstructor
@Setter
@Getter
@ToString(of = {"id", "name", "fieldType"})
@EqualsAndHashCode(of = {"id"})
public class ReportFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type")
    private String fieldType;

    @Column(name = "table_ref_name")
    private String tableRefName;

    @Column(name = "is_summable")
    private Boolean isSummable;

    @Column(name = "is_groupable")
    private Boolean isGroupable;

    @Column(name = "is_editable")
    private Boolean isEditable;

}
