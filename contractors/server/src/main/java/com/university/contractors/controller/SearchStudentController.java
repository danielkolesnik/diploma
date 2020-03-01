package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.controller.payload.SearchStudent;
import com.university.contractors.model.SearchStudentResult;
import com.university.contractors.repository.SearchStudentRepository;
import com.university.contractors.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.hibernate.TransientObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(
        value = Endpoints.SEARCH,
        produces = MediaType.APPLICATION_JSON,
        name = "Search Student Management Controller"
)
@Api(tags = "Search Student Management")
public class SearchStudentController {

    private final SearchStudentRepository searchStudentRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    public SearchStudentController(SearchStudentRepository searchStudentRepository) {
        this.searchStudentRepository = searchStudentRepository;
    }

    /**
     * Get Students List filtered
     *
     * @param searchStudent    filter
     * @return Students List
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public List<SearchStudentResult> search(@RequestBody SearchStudent searchStudent) {
        try {
//            return searchStudentRepository.search(searchStudent);
            return studentService.getSearchListFiltered(searchStudent);
        } catch (RuntimeException exception) {
            if (isCausedByTransientObjectException(exception)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs of all entities should be specified to execute search");
            }
            throw exception;
        }
    }

    /**
     * Helper for catching Specific type of Exceptions
     *
     * @param exception    RuntimeException to check
     * @return
     */
    private boolean isCausedByTransientObjectException(RuntimeException exception) {
        Throwable currentCause = exception;
        while (Objects.nonNull(currentCause)) {
            if (currentCause instanceof TransientObjectException) {
                return true;
            }

            currentCause = currentCause.getCause();
        }
        return false;
    }
}
