package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.StudentStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(
        value = Endpoints.STUDENT_STATUSES,
        produces = MediaType.APPLICATION_JSON,
        name = "Student Statuses Management Controller"
)
@Api(tags = "Student Statuses Management")
public class StudentStatusController extends AbstractCrudControllerBase<Long, StudentStatus> {

    public StudentStatusController(CrudRepository<StudentStatus, Long> crudRepository) {
        super(crudRepository);
    }

    /**
     * Get Student Status Item
     *
     * @param itemId    item identifier
     * @return Student Status item
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public StudentStatus getById (
        @PathVariable Long itemId
    ) {
        return super.getById(itemId);
    }

    /**
     * Get Student Status List
     *
     * @return Student Status List
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Iterable<StudentStatus> getAll() {
        return super.getAll();
    }

    /**
     * Create new Student Status
     *
     * @param entityToCreate    create entity
     * @return New Student Status
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public StudentStatus create (
        @RequestBody StudentStatus entityToCreate
    ) {
        return super.create(entityToCreate);
    }

    /**
     * Update Student Status
     *
     * @param itemId    item to update identifier
     * @param entityToUpdateWith    update entity
     * @return Updated Student Status
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public StudentStatus update (
        @PathVariable Long itemId,
        @RequestBody StudentStatus entityToUpdateWith
    ) {
        return super.update(itemId, entityToUpdateWith);
    }

    /**
     * Deletes Student Status
     *
     * @param itemId    item to delete identifier
     * @return ID of removed Student Status
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
