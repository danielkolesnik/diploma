package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.dto.reports.ReportDTO;
import com.university.contractors.model.dto.reports.ReportFieldUploadDTO;
import com.university.contractors.model.dto.reports.ReportFieldViewDTO;
import com.university.contractors.model.jpa.domains.ReportFormat;
import com.university.contractors.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(
        value = Endpoints.REPORT,
        produces = MediaType.APPLICATION_JSON,
        name = "Reports Management Controller"
)
@Api(tags = "Reports Management")
public class ReportController {

    @Autowired
    private ReportService reportService;


    /**
     * Get Report Fields List
     *
     * @return Report Fields List
     */
    @RequestMapping(value = "/fields", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public List<ReportFieldViewDTO> getFieldsList() {
        List<ReportFieldViewDTO> result = reportService.getFieldsList();

        return result;
    }


    /**
     * Generate HTML Report
     *
     */
    @RequestMapping(value = "/fields", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public void getFieldsList(
        HttpServletResponse response,
        @ApiParam(value = "Report Details", required = true) @RequestBody ReportDTO reportDetails
    ) throws IOException {

        // Build Download Template
        ByteArrayOutputStream byteArrayOutputStream = reportService.getDownloadTemplate(reportDetails);

        // Build HTTP Response
        if (reportDetails.getFormat().equals(ReportFormat.HTML)) {
            response.setHeader("Content-Disposition", MessageFormat.format("attachment; filename=report.{0}.html", new SimpleDateFormat("yyyyMMdd").format(new Date())));
        } else if (reportDetails.getFormat().equals(ReportFormat.XLS)) {
            response.setHeader("Content-Disposition", MessageFormat.format("attachment; filename=report.{0}.xlsx", new SimpleDateFormat("yyyyMMdd").format(new Date())));
        }

        OutputStream outputStream = response.getOutputStream();
        byteArrayOutputStream.writeTo(outputStream);
    }
}
