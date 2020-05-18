package com.university.contractors.model.jpa.entity;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Common Student Report View Entity Definition
 *
 * @author    Daniel A. Kolesnik <daneelkolesnik@gmail.com>
 * @version   0.1.0
 * @since     2020-03-14
 */
@Entity
@Immutable
@Table(name = "common_student_report")
@Subselect("select uuid() as id, csr.* from common_student_report csr")
public class CommonStudentReportView {

    @Id
    private String id;

    @Column(name = "id_student")
    private Long studentId;

    @Column(name = "stud_surname")
    private String studSurname;

    @Column(name = "stud_name")
    private String studName;

    @Column(name = "stud_middlename")
    private String studMiddlename;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "id_contract")
    private Long idContract;

    @Column(name = "contract_date")
    private Date contractDate	;

    @Column(name = "contract_value")
    private String contractValue;

    @Column(name = "contract_number")
    private String contractNumber;

    @Column(name = "ref_order_project")
    private String refOrderProject;

    @Column(name = "ref_dedact_order_project")
    private String refDedactOrderProject;

    @Column(name = "payer")
    private String payer;

    @Column(name = "date_in")
    private Date dateIn;

    @Column(name = "plan_date_out")
    private Date planDateOut;

    @Column(name = "course")
    private String course;

    @Column(name = "years_of_educ")
    private String yearsOfEduc;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_budget")
    private Boolean isBudget;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "id_contract_type")
    private Long idContractType;

    @Column(name = "contract_type_name")
    private String contractTypeName;

    @Column(name = "id_student_status")
    private Long idStudentStatus;

    @Column(name = "student_status_name")
    private String studentStatusName;

    @Column(name = "id_country")
    private Long idCountry;

    @Column(name = "country_name_ua")
    private String countryNameUa;

    @Column(name = "country_name_eng")
    private String countryNameEng;

    @Column(name = "country_name_ru")
    private String countryNameRu;

    @Column(name = "id_direction")
    private Long idDirection;

    @Column(name = "direction_name")
    private String directionName;

    @Column(name = "id_faculty")
    private Long idFaculty;

    @Column(name = "faculty_name")
    private String facultyName;

    @Column(name = "faculty_name_short")
    private String facultyNameShort;

    @Column(name = "faculty_name_eng")
    private String facultyNameEng;

    @Column(name = "faculty_name_ru")
    private String facultyNameRu;

    @Column(name = "id_educ_language")
    private Long idEducLanguage;

    @Column(name = "educ_language_name")
    private String educLanguageName;

    @Column(name = "id_educ_form")
    private Long idEducForm;

    @Column(name = "educ_form_name")
    private String educFormName;

    @Column(name = "id_educ_level")
    private Long idEducLevel;

    @Column(name = "educ_level_name")
    private String educLevelName;

    @Column(name = "number_of_month")
    private String numberOfMonth;

    @Column(name = "is_summer_month")
    private Boolean isSummerMonth;

    @Column(name = "id_educ_prog")
    private Long idEducProg;

    @Column(name = "educ_prog_name")
    private String educProgName;

    @Column(name = "id_arrival_line")
    private Long idArrivalLine;

    @Column(name = "arrival_center")
    private String arrivalCenter;

    @Column(name = "arrival_center_name")
    private String arrivalCenterName;

    @Column(name = "id_in_order")
    private Long idInOrder;

    @Column(name = "in_order_number")
    private String inOrderNumber;

    @Column(name = "in_order_date")
    private Date inOrderDate;

    @Column(name = "in_related_date")
    private Date inRelatedDate;

    @Column(name = "id_out_order")
    private Long idOutOrder;

    @Column(name = "out_order_number")
    private String outOrderNumber;

    @Column(name = "out_order_date")
    private Date outOrderDate;

    @Column(name = "out_related_date")
    private Date outRelatedDate;

    @Column(name = "out_order_note")
    private String outOrderNote;

}
