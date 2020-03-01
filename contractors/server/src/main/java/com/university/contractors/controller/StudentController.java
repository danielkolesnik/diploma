package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Student;
import com.university.contractors.repository.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(
        value = Endpoints.STUDENTS,
        produces = MediaType.APPLICATION_JSON,
        name = "Students Management Controller"
)
@Api(tags = "Students Management")
public class StudentController extends AbstractCrudControllerBase<Long, Student> {

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        super(studentRepository);
    }

    /**
     * Get Student Item
     *
     * @param itemId    item identifier
     * @return Student item
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Student getById (
        @PathVariable Long itemId
    ) {
        return super.getById(itemId);
    }

    /**
     * Get Student List
     *
     * @return Student List
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Iterable<Student> getAll() {
        return super.getAll();
    }

    /**
     * Create new Student
     *
     * @param entityToCreate    create entity
     * @return New Student
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Student create (
        @RequestBody Student entityToCreate
    ) {
        return super.create(entityToCreate);
    }

    /**
     * Update Student
     *
     * @param itemId    item to update identifier
     * @param entityToUpdateWith    update entity
     * @return Updated Student
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Student update (
        @PathVariable Long itemId,
        @RequestBody Student entityToUpdateWith
    ) {
        return super.update(itemId, entityToUpdateWith);
    }

    /**
     * Deletes Student
     *
     * @param itemId    item to delete identifier
     * @return ID of removed Student
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Long delete (
        @PathVariable Long itemId
    ) {
        return super.delete(itemId);
    }
}
