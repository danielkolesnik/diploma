package com.university.contractors.service;

import com.university.contractors.controller.payload.SearchStudent;
import com.university.contractors.model.SearchStudentResult;
import com.university.contractors.model.Student;
import com.university.contractors.model.dao.StudentModelDAO;
import com.university.contractors.model.dto.students.DTOBase;
import com.university.contractors.model.dto.students.StudentViewDTO;
import com.university.contractors.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentModelDAO studentModelDAO;

    /**
     * Get Students List
     *
     * @return items List
     */
    public List<StudentViewDTO> getList() {
        List<Student> items = studentRepository.findAll();

        List<StudentViewDTO> result = DTOBase.fromEntitiesList(items, StudentViewDTO.class);

        return result;
    }

    // TODO: implement CRUD methods and use this service in controller

    public List<SearchStudentResult> getSearchListFiltered(SearchStudent filter) {
        filter.setIsBudget(Boolean.FALSE);
        filter.setIsActive(Boolean.TRUE);
        List<SearchStudentResult> result = studentModelDAO.getItemsPageable(filter);

        return result;
    }
}
