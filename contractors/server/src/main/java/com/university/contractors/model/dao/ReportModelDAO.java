package com.university.contractors.model.dao;

import com.university.contractors.model.dto.reports.ReportFieldUploadDTO;
import com.university.contractors.model.dto.reports.ReportFieldViewDTO;
import com.university.contractors.model.dto.students.DTOBase;
import com.university.contractors.model.jpa.domains.ReportFieldType;
import com.university.contractors.model.jpa.entity.CommonStudentPaymentReportView;
import com.university.contractors.model.jpa.entity.ReportFields;
import com.university.contractors.repository.jpa.ReportFieldsRepository;

import com.university.contractors.repository.results.ReportFieldOption;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.university.contractors.model.jpa.domains.ReportFieldName.*;

/**
 * Report DAO Model
 *
 * @author   Daniel A. Kolesnik <daneelkolesnik@gmail.com>
 * @since    2020-03-01
 */
@Service
public class ReportModelDAO {

    @Autowired
    private ReportFieldsRepository reportFieldsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Tables Names to Query String
    private final static Map<String, String> tablesMap = Map.ofEntries(
        Map.entry("country", "SELECT new com.university.contractors.repository.results.ReportFieldOption(c.id, c.countryNameRu) FROM Country c"),
        Map.entry("faculty", "SELECT new com.university.contractors.repository.results.ReportFieldOption(f.id, f.facultyName) FROM Faculty f"),
        Map.entry("direction", "SELECT new com.university.contractors.repository.results.ReportFieldOption(d.id, d.directionName) FROM Direction d"),
        Map.entry("education_form", "SELECT new com.university.contractors.repository.results.ReportFieldOption(ef.id, ef.educFormName) FROM EducationForm ef"),
        Map.entry("education_level", "SELECT new com.university.contractors.repository.results.ReportFieldOption(el.id, el.educLevelName) FROM EducationLevel el"),
        Map.entry("education_program", "SELECT new com.university.contractors.repository.results.ReportFieldOption(ep.id, ep.educProgName) FROM EducationProgram ep"),
        Map.entry("arrival_line", "SELECT new com.university.contractors.repository.results.ReportFieldOption(al.id, al.arrivalCenterName) FROM ArrivalLine al"),
        Map.entry("educ_languages", "SELECT new com.university.contractors.repository.results.ReportFieldOption(el.id, el.educLanguageName) FROM EducationLanguage el"),
        Map.entry("student_status", "SELECT new com.university.contractors.repository.results.ReportFieldOption(ss.id, ss.studentStatusName) FROM StudentStatus ss"),
        Map.entry("contract_type", "SELECT new com.university.contractors.repository.results.ReportFieldOption(ct.id, ct.contractTypeName) FROM ContractType ct"),
        Map.entry("dedact_reason", "SELECT new com.university.contractors.repository.results.ReportFieldOption(dr.id, dr.reasonName) FROM DeductReason dr")
    );

    // Fields Ids to WHERE Strings
    private final static Map<Long, String> filterFieldsMap = Map.ofEntries(
        // Student Surname (Фамилия)
        Map.entry(STUDENT_SURNAME.getId(), " AND UPPER(cspr.studSurname) LIKE (CONCAT(UPPER(:studentSurname), '%')) "),
        // Student Name (Имя)
        Map.entry(STUDENT_NAME.getId(), " AND UPPER(cspr.studName) LIKE (CONCAT(UPPER(:studentName), '%')) "),
        // Student Middlename (Отчество)
        Map.entry(STUDENT_MIDDLENAME.getId(), " AND UPPER(cspr.studMiddlename) LIKE (CONCAT(UPPER(:studentMiddlename), '%')) "),
        // Country (Страна)
        Map.entry(COUNTRY.getId()," AND cspr.idCountry IN :countries "),
        // Date of Birth (Дата рождения) - not editable
//        Map.entry(BIRTHDATE," AND (year(cspr.dateOfBirth) = year(:birthDate) AND month(cspr.dateOfBirth) = month(:birthDate) AND day(cspr.dateOfBirth) = day(:birthDate)) "),
        // Faculty (Факультет)
        Map.entry(FACULTY.getId()," AND cspr.idFaculty IN :faculties "),
        // Direction (Направление)
        Map.entry(DIRECTION.getId()," AND cspr.idDirection IN :directions "),
        // Education Form (Форма обучения)
        Map.entry(EDUCATION_FORM.getId()," AND cspr.idEducForm IN :educationForms "),
        // Education Level (Уроверь обучения)
        Map.entry(EDUCATION_LEVEL.getId()," AND cspr.idEducLevel IN :educationLevels "),
        // Education Program (Образовательная программа)
        Map.entry(EDUCATION_PROGRAM.getId()," AND cspr.idEducProg IN :educationPrograms "),
        // Entrance Date (Дата зачисления) - not editable
//        Map.entry(ENTRANCE_DATE," AND (year(cspr.dateIn) = year(:entranceDate) AND month(cspr.dateIn) = month(:entranceDate) AND day(cspr.dateIn) = day(:entranceDate)) "),
        // Graduation Date (Дата окончания)
//        Map.entry(GRADUATION_DATE," AND (year(cspr.planDateOut) = year(:graduationDate) AND month(cspr.planDateOut) = month(:graduationDate) AND day(cspr.planDateOut) = day(:graduationDate)) "),
        // Arrival Line (Линия прибытия)
        Map.entry(ARRIVAL_LINE.getId()," AND cspr.idArrivalLine IN :arrivalLines "),
        // Course (Курс)
        Map.entry(COURSE.getId()," AND cspr.course IN :courses "),
        // Education Language (Язык обучения)
        Map.entry(EDUCATION_LANGUAGE.getId()," AND cspr.idEducLanguage IN :educationLanguages "),
        // Status (Статус)
        Map.entry(STATUS.getId()," AND cspr.idStudentStatus IN :studentStatuses "),
        // Enrollment (Набор)
        // TODO find out strategy for filtering values with ENROLLMENT
//        Map.entry(ENROLLMENT,""),
        // Contract Value (Сумма контракта) - not editable
//        Map.entry(CONTRACT_VALUE," AND cspr.contractValue = :contractTotalAmount "),
        // Enrollment Order Number (Номер приказа зачисл)
        Map.entry(ENROLLMENT_ORDER_NUMBER.getId()," AND UPPER(cspr.inOrderNumber) LIKE (CONCAT(UPPER(:inOrderNumber), '%')) "),
        // Contract number (Номер контракта)
        Map.entry(CONTRACT_NUMBER.getId()," AND UPPER(cspr.contractNumber) LIKE (CONCAT(UPPER(:contractNumber), '%')) "),
        // Contract Type (Тип контракта)
        Map.entry(CONTRACT_TYPE.getId()," AND cspr.idContractType IN :contractTypes "),
        // Payer (Плательщик) - not editable
//        Map.entry(PAYER," AND UPPER(cspr.payer) LIKE (CONCAT(UPPER(:payer), '%')) "),
        // Prepay in HRN (Предоплата в гривнах) - not editable
//        Map.entry(PREPAY_HRN,""),
        // Prepay in USD (Предоплата в валюте) - not editable
//        Map.entry(PREPAY_USD,""),
        // Payment in HRN (Оплата в гривнах)
//        Map.entry(PAYMENT_HRN,""),
        // Payment in USD (Оплата в валюте)
//        Map.entry(PAYMENT_USD,""),
        // Debt in HRN (Долг в гривнах)
        // TODO find out which field from CommonStudentPaymentReportView it should be mapped to
        Map.entry(DEBT_HRN.getId(),""),
        // Debt in USD (Долг в валюте)
        // TODO find out which field from CommonStudentPaymentReportView it should be mapped to
        Map.entry(DEBT_USD.getId(),""),
        // Fine in HRN (Пеня долг в гривнах)
        // TODO find out which field from CommonStudentPaymentReportView it should be mapped to
        Map.entry(FINE_HRN.getId(),""),
        // Fine in USD (Пеня дол в валюте)
        // TODO find out which field from CommonStudentPaymentReportView it should be mapped to
        Map.entry(FINE_USD.getId(),""),
        // Period from (Период с)
        // TODO find out which field from CommonStudentPaymentReportView it should be mapped to
        Map.entry(PERIOD_FROM.getId(),""),
        // Period to (Период по)
        // TODO find out which field from CommonStudentPaymentReportView it should be mapped to
        Map.entry(PERIOD_TO.getId(),""),
        // Enrollment Order Date (Дата приказа зачисл) - inOrderDate
        Map.entry(ENROLLMENT_ORDER_DATE.getId()," AND (year(cspr.inOrderDate) = year(:inOrderDate) AND month(cspr.inOrderDate) = month(:inOrderDate) AND day(cspr.inOrderDate) = day(:inOrderDate)) "),
        // Nationality (Национальность)
        Map.entry(NATIONALITY.getId()," AND cspr.nationality IN :nationalities"),
        // Deduction order number (Номер приказа отчисления)
        Map.entry(DEDUCTION_ORDER_NUMBER.getId()," AND UPPER(cspr.outOrderNumber) LIKE (CONCAT(UPPER(:outOrderNumber), '%')) "),
        // Deduction order date (Дата приказа отчисления)
        Map.entry(DEDUCTION_ORDER_DATE.getId()," AND (year(cspr.outOrderDate) = year(:outOrderDate) AND month(cspr.outOrderDate) = month(:outOrderDate) AND day(cspr.outOrderDate) = day(:outOrderDate)) "),
        // Deduction reason (Причина отчисления)
        Map.entry(DEDUCTION_REASON.getId()," AND (UPPER(cspr.outOrderNote)) LIKE (CONCAT(UPPER(:outOrderNote), '%')) ")
    );

    private final static Map<Long, String> groupFieldsMap = Map.ofEntries(
        Map.entry(COUNTRY.getId(), " cspr.idCountry "),
        Map.entry(FACULTY.getId(), " cspr.idFaculty "),
        Map.entry(DIRECTION.getId(), " cspr.idDirection "),
        Map.entry(EDUCATION_FORM.getId(), " cspr.idEducForm "),
        Map.entry(EDUCATION_LEVEL.getId(), " cspr.idEducLevel "),
        Map.entry(EDUCATION_PROGRAM.getId(), " cspr.idEducProg "),
        Map.entry(ARRIVAL_LINE.getId(), " cspr.idArrivalLine "),
        Map.entry(COURSE.getId(), " cspr.course "),
        Map.entry(EDUCATION_LANGUAGE.getId(), " cspr.idEducLanguage "),
        Map.entry(STATUS.getId(), " cspr.idStudentStatus "),
//        Map.entry(ENROLLMENT.getId(), ""),
        Map.entry(NATIONALITY.getId(), " cspr.nationality"),
        Map.entry(CONTRACT_TYPE.getId(), "cspr.idContractType")
    );

    /**
     * Get Report Fields View DTO List
     *
     * @return Report Fields View DTO List
     */
    public List<ReportFieldViewDTO> getReportFieldsList() {
        List<ReportFields> fields = reportFieldsRepository.getList();
        List<ReportFieldViewDTO> result = new ArrayList<>();

        fields.forEach(reportField -> {
            ReportFieldViewDTO fieldDTO = new ReportFieldViewDTO(reportField);
            fieldDTO.fromEntity(reportField);

            if(ReportFieldType.DROPDOWN.getValue().equals(reportField.getFieldType())) {
                List<ReportFieldOption> fieldOptions = new ArrayList<>();

                // TODO create data base tables for course, enrollment and nationality options
                if (reportField.getId().equals(COURSE.getId())) {
                    // Creation of 7 options (1st course, 2nd course ...
                    for (Integer i = 1; i < 7; i++) {
                        fieldOptions.add(new ReportFieldOption((long) i, i.toString()));
                    }

                } else if (reportField.getId().equals(ENROLLMENT.getId())) {
                    fieldOptions.add(new ReportFieldOption(0L, "Зима"));
                    fieldOptions.add(new ReportFieldOption(1L, "Осень"));

                } else if (reportField.getId().equals(NATIONALITY.getId())) {
                    fieldOptions.add(new ReportFieldOption(0L, "гр. Украины"));
                    fieldOptions.add(new ReportFieldOption(1L, "Иностранный гр."));
                }

                if (StringUtils.isNotEmpty(reportField.getTableRefName())) {
                    String hqlQuery = tablesMap.get(reportField.getTableRefName());
                    TypedQuery<ReportFieldOption> typedQuery = entityManager.createQuery(hqlQuery, ReportFieldOption.class);

                    fieldOptions.addAll(typedQuery.getResultList());
                }

                fieldDTO.setOptions(fieldOptions);
            }

            result.add(fieldDTO);
        });

        return result;
    }

    /**
     * Get Report Entries List
     *
     * @return Report Fields View DTO List
     */
    public List<CommonStudentPaymentReportView> getReportEntriesList(List<ReportFieldUploadDTO> filterFields) {

        // Define base hql data Query
        String hqlQuery = "SELECT cspr FROM CommonStudentPaymentReportView cspr ";

        // Build Query String

        // Should be always true to be able to dynamically add any additional conditions
        // and guaranty that in case of no additional conditions came query will work just fine
        String whereString = " WHERE cspr.studentId IS NOT NULL ";
        String groupByString = "";
        List<String> groupFields = new ArrayList<>();

        for (ReportFieldUploadDTO filterField: filterFields) {

            if (filterField.getIsEditable()) {
                if (Optional.ofNullable(filterFieldsMap.get(filterField.getId())).isPresent() && filterField.getValues() != null && filterField.getValues().size() > 0) {
                    whereString += filterFieldsMap.get(filterField.getId());
                    // TODO remove
                    System.out.println(MessageFormat.format("\n\n\t\t[[whereString]] New condition added ({0} - {1}) ---> {2}", filterField.getId(), filterField.getName(), filterFieldsMap.get(filterField.getId())));

                    if (filterField.getIsGroupable()) {
                        if (Optional.ofNullable(groupFieldsMap.get(filterField.getId())).isPresent()) {
                            groupFields.add(groupFieldsMap.get(filterField.getId()));
                        } else {
                            // TODO remove after mapping all of the existed fields in [[groupFieldsMap]]
                            System.out.println(MessageFormat.format("\n\n\t\t[[groupFieldsMap]] ReportField ({0} - {1}) isGroupable = true but field didn't match any of the available options to map from groupFieldsMap \n\t\tSKIP FIELD...", filterField.getId(), filterField.getName()));
                        }
                    }

                } else {
                    // TODO remove after mapping all of the existed fields in [[filterFieldsMap]]
                    System.out.println(MessageFormat.format("\n\n\t\t[[filterFieldsMap]] ReportField ({0} - {1}) didn't match any of the available options to map or its values are empty \n\t\tSKIP FIELD...", filterField.getId(), filterField.getName()));
                }
            } else {
                // TODO remove
                System.out.println(MessageFormat.format("\n\n\t\t[[isEditable]] ReportField ({0} - {1}) is not editable so it shouldn't be mapped \n\t\tSKIP FIELD...", filterField.getId(), filterField.getName()));
            }
        }

        if (groupFields.size() > 0) {
            groupByString = " ORDER BY ".concat(String.join(", ", groupFields));
            // TODO remove
            System.out.println("\n\n\t\tGROUP BY STRING - ".concat(groupByString));
        }

        // Build Query data
        String searchQueryString = hqlQuery + whereString + groupByString;
        // TODO remove
        System.out.println("\n\n\t\t[[searchQueryString]] \n\n".concat(searchQueryString));
        System.out.println("\n\n");

        TypedQuery<CommonStudentPaymentReportView> typedQuery = entityManager.createQuery(searchQueryString, CommonStudentPaymentReportView.class);
        applySearchFilterValues(filterFields, typedQuery);
        List<CommonStudentPaymentReportView> resultList = typedQuery.getResultList();

        return resultList;
    }

    /**
     * Apply query data
     *
     * @param filterFields
     * @param typedQuery
     */
    private void applySearchFilterValues(List<ReportFieldUploadDTO> filterFields, Query typedQuery) {

        for (ReportFieldUploadDTO filterField: filterFields) {

            if (filterField.getId().equals(STUDENT_SURNAME.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                typedQuery.setParameter("studentSurname", filterField.getValues().get(0).getValue());

            } else if (filterField.getId().equals(STUDENT_NAME.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                typedQuery.setParameter("studentName", filterField.getValues().get(0).getValue());

            } else if (filterField.getId().equals(STUDENT_MIDDLENAME.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                typedQuery.setParameter("studentMiddlename", filterField.getValues().get(0).getValue());

            } else if (filterField.getId().equals(COUNTRY.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                typedQuery.setParameter("countries", selectedOptionsIds);

            } else if (filterField.getId().equals(FACULTY.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                typedQuery.setParameter("faculties", selectedOptionsIds);

            } else if (filterField.getId().equals(DIRECTION.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                typedQuery.setParameter("directions", selectedOptionsIds);

            } else if (filterField.getId().equals(EDUCATION_FORM.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                typedQuery.setParameter("educationForms", selectedOptionsIds);

            } else if (filterField.getId().equals(EDUCATION_LEVEL.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                typedQuery.setParameter("educationLevels", selectedOptionsIds);

            } else if (filterField.getId().equals(EDUCATION_PROGRAM.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                typedQuery.setParameter("educationPrograms", selectedOptionsIds);

            } else if (filterField.getId().equals(ARRIVAL_LINE.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                typedQuery.setParameter("arrivalLines", selectedOptionsIds);

            } else if (filterField.getId().equals(COURSE.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                List<Long> selectedOptionsValues = filterField.getValues().stream().map(ReportFieldOption::getValue).map(Long::parseLong).collect(Collectors.toList());
                typedQuery.setParameter("courses", selectedOptionsValues);

            } else if (filterField.getId().equals(EDUCATION_LANGUAGE.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                typedQuery.setParameter("educationLanguages", selectedOptionsIds);

            } else if (filterField.getId().equals(STATUS.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                typedQuery.setParameter("studentStatuses", selectedOptionsIds);

            } else if(filterField.getId().equals(ENROLLMENT.getId())) {
                // TODO find out strategy for filtering values with ENROLLMENT

            } else if (filterField.getId().equals(ENROLLMENT_ORDER_NUMBER.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                typedQuery.setParameter("inOrderNumber", filterField.getValues().get(0).getValue());

            } else if (filterField.getId().equals(CONTRACT_NUMBER.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                typedQuery.setParameter("contractNumber", filterField.getValues().get(0).getValue());

            } else if (filterField.getId().equals(CONTRACT_TYPE.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                typedQuery.setParameter("contractTypes", selectedOptionsIds);

            } else if(filterField.getId().equals(DEBT_HRN.getId())) {
                // TODO find out strategy for filtering values with DEBT_HRN

            } else if(filterField.getId().equals(DEBT_USD.getId())) {
                // TODO find out strategy for filtering values with DEBT_USD

            } else if(filterField.getId().equals(FINE_HRN.getId())) {
                // TODO find out strategy for filtering values with FINE_HRN

            } else if(filterField.getId().equals(FINE_USD.getId())) {
                // TODO find out strategy for filtering values with ENROLLMENT

            } else if(filterField.getId().equals(PERIOD_FROM.getId())) {
                // TODO find out strategy for filtering values with ENROLLMENT

            } else if(filterField.getId().equals(PERIOD_TO.getId())) {
                // TODO find out strategy for filtering values with ENROLLMENT

            } else if (filterField.getId().equals(ENROLLMENT_ORDER_DATE.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                try {
                    Date dateValue = dateFormat.parse(filterField.getValues().get(0).getValue());
                    typedQuery.setParameter("inOrderDate", dateValue);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    // TODO handle wrong inOrderDate date format
                }

            } else if (filterField.getId().equals(NATIONALITY.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                List<String> selectedOptionsValues = Arrays.asList("гр. Украины", "Иностранный гр.");
                typedQuery.setParameter("nationalities", selectedOptionsValues);

            } else if (filterField.getId().equals(DEDUCTION_ORDER_NUMBER.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                typedQuery.setParameter("outOrderNumber", filterField.getValues().get(0).getValue());

            } else if (filterField.getId().equals(DEDUCTION_ORDER_DATE.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                try {
                    Date dateValue = dateFormat.parse(filterField.getValues().get(0).getValue());
                    typedQuery.setParameter("outOrderDate", dateValue);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    // TODO handle wrong outOrderDate date format
                }

            } else if (filterField.getId().equals(DEDUCTION_REASON.getId()) && filterField.getValues() != null && filterField.getValues().size() > 0) {
                typedQuery.setParameter("outOrderNote", filterField.getValues().get(0).getValue());
            }

            // TODO remove
            /*
            switch (filterField.getId().intValue()) {
                case 2:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        typedQuery.setParameter("studentSurname", filterField.getValues().get(0).getValue());
                    }
                    break;
                case 3:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        typedQuery.setParameter("studentName", filterField.getValues().get(0).getValue());
                    }
                    break;
                case 4:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        typedQuery.setParameter("studentMiddlename", filterField.getValues().get(0).getValue());
                    }
                    break;
                case 5:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                        typedQuery.setParameter("countries", selectedOptionsIds);
                    }
                    break;
                case 7:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                        typedQuery.setParameter("faculties", selectedOptionsIds);
                    }
                    break;
                case 8:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                        typedQuery.setParameter("directions", selectedOptionsIds);
                    }
                    break;
                case 9:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                        typedQuery.setParameter("educationForms", selectedOptionsIds);
                    }
                    break;
                case 10:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                        typedQuery.setParameter("educationLevels", selectedOptionsIds);
                    }
                    break;
                case 11:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                        typedQuery.setParameter("educationPrograms", selectedOptionsIds);
                    }
                    break;
                case 14:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                        typedQuery.setParameter("arrivalLines", selectedOptionsIds);
                    }
                    break;
                case 15:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        List<Long> selectedOptionsValues = filterField.getValues().stream().map(ReportFieldOption::getValue).map(Long::parseLong).collect(Collectors.toList());
                        typedQuery.setParameter("courses", selectedOptionsValues);
                    }
                    break;
                case 16:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                        typedQuery.setParameter("educationLanguages", selectedOptionsIds);
                    }
                    break;
                case 17:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                        typedQuery.setParameter("studentStatuses", selectedOptionsIds);
                    }
                    break;
                case 18:
                    // TODO find out strategy for filtering values with ENROLLMENT
                    break;
                case 20:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        typedQuery.setParameter("inOrderNumber", filterField.getValues().get(0).getValue());
                    }
                    break;
                case 21:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        typedQuery.setParameter("contractNumber", filterField.getValues().get(0).getValue());
                    }
                    break;
                case 22:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        List<Long> selectedOptionsIds = filterField.getValues().stream().map(ReportFieldOption::getId).collect(Collectors.toList());
                        typedQuery.setParameter("contractTypes", selectedOptionsIds);
                    }
                    break;
                case 28:
                    // TODO find out strategy for filtering values with ENROLLMENT
                    break;
                case 29:
                    // TODO find out strategy for filtering values with ENROLLMENT
                    break;
                case 30:
                    // TODO find out strategy for filtering values with ENROLLMENT
                    break;
                case 31:
                    // TODO find out strategy for filtering values with ENROLLMENT
                    break;
                case 32:
                    // TODO find out strategy for filtering values with ENROLLMENT
                    break;
                case 33:
                    // TODO find out strategy for filtering values with ENROLLMENT
                    break;
                case 35:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        try {
                            Date dateValue = dateFormat.parse(filterField.getValues().get(0).getValue());
                            typedQuery.setParameter("inOrderDate", dateValue);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            // TODO handle wrong inOrderDate date format
                        }
                    }
                    break;
                case 36:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        List<String> selectedOptionsValues = Arrays.asList("гр. Украины", "Иностранный гр.");
                        typedQuery.setParameter("nationalities", selectedOptionsValues);
                    }
                    break;
                case 37:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        typedQuery.setParameter("outOrderNumber", filterField.getValues().get(0).getValue());
                    }
                    break;
                case 38:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        try {
                            Date dateValue = dateFormat.parse(filterField.getValues().get(0).getValue());
                            typedQuery.setParameter("outOrderDate", dateValue);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            // TODO handle wrong outOrderDate date format
                        }
                    }
                    break;
                case 39:
                    if (filterField.getValues() != null && filterField.getValues().size() > 0) {
                        typedQuery.setParameter("outOrderNote", filterField.getValues().get(0).getValue());
                    }
                    break;
            }
            */
        }

    }
}
