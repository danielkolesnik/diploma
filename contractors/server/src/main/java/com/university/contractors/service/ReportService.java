package com.university.contractors.service;

import com.university.contractors.model.dao.ReportModelDAO;
import com.university.contractors.model.dto.reports.ReportFieldViewDTO;
import com.university.contractors.repository.jpa.ReportFieldsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportFieldsRepository reportFieldsRepository;

    @Autowired
    private ReportModelDAO reportModelDAO;

    /**
     * Get Report Fields List
     *
     * @return Report Fields List
     */
    public List<ReportFieldViewDTO> getFieldsList() {
        List<ReportFieldViewDTO> result = reportModelDAO.getReportFieldsList();

        return result;
    }


}
