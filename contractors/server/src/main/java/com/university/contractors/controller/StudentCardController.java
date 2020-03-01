package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.StudentCard;
import com.university.contractors.service.StudentCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(
        value = Endpoints.STUDENT_CARD,
        produces = MediaType.APPLICATION_JSON,
        name = "Student Cards Management Controller"
)
@Api(tags = "Student Cards Management")
public class StudentCardController {

    private final StudentCardService studentCardService;

    @Autowired
    public StudentCardController(StudentCardService studentCardService) {
        this.studentCardService = studentCardService;
    }

    /**
     * Get Country Item
     *
     * @param itemId    item identifier
     * @return Country item
     */
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public StudentCard getById (
        @PathVariable Long itemId
    ) {
        return studentCardService.findStudentCard(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
