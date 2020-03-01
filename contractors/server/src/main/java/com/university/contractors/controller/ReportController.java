package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.dto.reports.ReportFieldViewDTO;
import com.university.contractors.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
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
}
