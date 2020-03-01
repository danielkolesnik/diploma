package com.university.contractors.model.dao;

import com.university.contractors.model.dto.reports.ReportFieldViewDTO;
import com.university.contractors.model.jpa.domains.ReportFieldType;
import com.university.contractors.model.jpa.entity.ReportFields;
import com.university.contractors.repository.jpa.ReportFieldsRepository;

import com.university.contractors.repository.results.ReportFieldOption;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    // TODO: find out better way
    private final Map<String, String> tablesMap = Map.ofEntries(
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

                // 15 is ID of Course(Курс) report field
                if (reportField.getId() == 15) {
                    // Creation of 7 options (1 course, 2 course ...
                    for (Integer i = 1; i <= 7; i++) {
                        fieldOptions.add(new ReportFieldOption((long) i, i.toString()));
                    }

                } else if (reportField.getId() == 18) {
                    fieldOptions.add(new ReportFieldOption(0L, "Зима"));
                    fieldOptions.add(new ReportFieldOption(1L, "Осень"));

                } else if (reportField.getId() == 36) {
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
}
