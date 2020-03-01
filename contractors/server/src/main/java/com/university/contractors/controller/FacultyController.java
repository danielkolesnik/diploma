package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Faculty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(
        value = Endpoints.FACULTIES,
        produces = MediaType.APPLICATION_JSON,
        name = "Faculties Management Controller"
)
@Api(tags = "Faculties Management")
public class FacultyController extends AbstractCrudControllerBase<Long, Faculty> {

    public FacultyController(CrudRepository<Faculty, Long> crudRepository) {
        super(crudRepository);
    }

    /**
     * Get Faculty Item
     *
     * @param itemId    item identifier
     * @return Faculty item
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Faculty getById (
        @PathVariable Long itemId
    ) {
        return super.getById(itemId);
    }

    /**
     * Get Faculty List
     *
     * @return Faculty List
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Iterable<Faculty> getAll() {
        return super.getAll();
    }

    /**
     * Create new Faculty
     *
     * @param entityToCreate    create entity
     * @return New Faculty
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Faculty create (
        @RequestBody Faculty entityToCreate
    ) {
        return super.create(entityToCreate);
    }

    /**
     * Update Faculty
     *
     * @param itemId    item to update identifier
     * @param entityToUpdateWith    update entity
     * @return Updated Faculty
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Faculty update (
        @PathVariable Long itemId,
        @RequestBody Faculty entityToUpdateWith
    ) {
        return super.update(itemId, entityToUpdateWith);
    }

    /**
     * Deletes Faculty
     *
     * @param itemId    item to delete identifier
     * @return ID of removed Faculty
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
