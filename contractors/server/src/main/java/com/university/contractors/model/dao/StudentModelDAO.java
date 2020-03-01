package com.university.contractors.model.dao;

import com.university.contractors.controller.payload.Nationality;
import com.university.contractors.controller.payload.SearchStudent;
import com.university.contractors.model.jpa.entity.Country;
import com.university.contractors.model.data.SearchStudentResult;
import com.university.contractors.service.CountryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import java.util.List;

/**
 * Student DAO Model
 *
 * @author   Daniel A. Kolesnik <daneelkolesnik@gmail.com>
 * @since    2020-02-29
 */
@Service
public class StudentModelDAO {

    @Autowired
    private CountryService countryService;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get Students Pageable
     * TODO: refactor to return Pageable, add sorting
     *
     * @param filter
     * @return
     */
    public List<SearchStudentResult> getItemsPageable (SearchStudent filter) {

        Country localCountry = countryService.getLocalCountry();

        // Define base hql data Query
        String hqlQuery = "SELECT new com.university.contractors.model.data.SearchStudentResult(c) FROM Contract c FETCH ALL PROPERTIES ";

        // Define base hql count Query
        String hqlQueryCount = "SELECT count(c) FROM Contract c FETCH ALL PROPERTIES ";

        // Build Query String
        String whereString = " WHERE c.isBudget = :isBudget AND c.isActive = :isActive";
        if (StringUtils.isNotEmpty(filter.getName())) {
            whereString += " AND UPPER(c.student.name) LIKE (CONCAT(UPPER(:studentName), '%')) ";
        }
        if (StringUtils.isNotEmpty(filter.getSurname())) {
            whereString += " AND UPPER(c.student.surname) LIKE (CONCAT(UPPER(:studentSurname), '%')) ";
        }
        if (filter.getDataOfBirth() != null) {
            whereString += " AND c.student.dateOfBirth = :dateOfBirth ";
        }
        if (filter.getNationality() != null) {
            if (filter.getNationality() == Nationality.LOCAL) {
                whereString += " AND c.student.country = :localCountry";
            } else {
                whereString += " AND c.student.country != :localCountry ";
            }
        }
        if (filter.getCountry() != null) {
            whereString += " AND c.student.country = :country ";
        }
        if (StringUtils.isNotEmpty(filter.getContractNumber())) {
            whereString += " AND c.contractNumber = :contractNumber ";
        }
        if (filter.getCourse() != null) {
            whereString += " AND c.course = :course ";
        }
        if (filter.getFaculty() != null) {
            whereString += " AND c.direction.faculty = :faculty";
        }
        if (filter.getDirection() != null) {
            whereString += " AND c.direction = :direction";
        }
        if (filter.getEducationProgram() != null) {
            whereString += " AND c.educationProgram = :educationProgram";
        }
        if (filter.getEducationLevel() != null) {
            whereString += " AND c.educationLevel = :educationLevel";
        }
        if (filter.getEducationForm() != null) {
            whereString += " AND c.educationForm = :educationForm";
        }
        if (filter.getArrivalLine() != null) {
            whereString += " AND c.arrivalLine = :arrivalLine";
        }
        if (filter.getEducationLanguage() != null) {
            whereString += " AND c.educationLanguage = :educationLanguage";
        }

        String searchQueryString = hqlQuery + whereString;
        // TODO: implement sorting here
        // ...
        searchQueryString += " ORDER BY c.contractNumber ASC ";

        // Build Query data
        TypedQuery<SearchStudentResult> typedQuery = entityManager.createQuery(searchQueryString, SearchStudentResult.class);
        applySearchFilterValues(filter, typedQuery, localCountry);
        List<SearchStudentResult> resultList = typedQuery.getResultList();

        // Calculate count query
        // TODO: implement pagination logic
        Query queryCount = entityManager.createQuery(hqlQueryCount + whereString);
        applySearchFilterValues(filter, queryCount, localCountry);
        Long resultCount = (Long) queryCount.getSingleResult();

//        return new PagedResult<>(resultList, resultCount);

        return resultList;
    }

    /**
     * Apply query data
     *
     * @param filter
     * @param query
     */
    private void applySearchFilterValues(SearchStudent filter, Query query, Country localCountry) {
        query.setParameter("isBudget", filter.getIsBudget());
        query.setParameter("isActive", filter.getIsActive());

        if (StringUtils.isNotEmpty(filter.getName())) query.setParameter("studentName", filter.getName());
        if (StringUtils.isNotEmpty(filter.getSurname())) query.setParameter("studentSurname", filter.getSurname());
        if (filter.getDataOfBirth() != null) query.setParameter("dateOfBirth", filter.getDataOfBirth());
        if (filter.getNationality() != null) query.setParameter("localCountry", localCountry);
        if (filter.getCountry() != null) query.setParameter("country", filter.getCountry());
        if (StringUtils.isNotEmpty(filter.getContractNumber())) query.setParameter("contractNumber", filter.getContractNumber());
        if (filter.getCourse() != null) query.setParameter("course", filter.getCourse());
        if (filter.getFaculty() != null) query.setParameter("faculty", filter.getFaculty());
        if (filter.getDirection() != null) query.setParameter("direction", filter.getDirection());
        if (filter.getEducationProgram() != null) query.setParameter("educationProgram", filter.getEducationProgram());
        if (filter.getEducationLevel() != null) query.setParameter("educationLevel", filter.getEducationLevel());
        if (filter.getEducationForm() != null) query.setParameter("educationForm", filter.getEducationForm());
        if (filter.getArrivalLine() != null) query.setParameter("arrivalLine", filter.getArrivalLine());
        if (filter.getEducationLanguage() != null) query.setParameter("educationLanguage", filter.getEducationLanguage());
    }
}
