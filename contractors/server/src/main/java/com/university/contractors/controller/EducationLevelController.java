package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.jpa.entity.EducationLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(
        value = Endpoints.EDUCATION_LEVELS,
        produces = MediaType.APPLICATION_JSON,
        name = "Education Levels Management Controller"
)
@Api(tags = "Education Levels Management")
public class EducationLevelController extends AbstractCrudControllerBase<Long, EducationLevel> {

    public EducationLevelController(CrudRepository<EducationLevel, Long> crudRepository) {
        super(crudRepository);
    }

    /**
     * Get Education Level Item
     *
     * @param itemId    item identifier
     * @return Education Level item
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public EducationLevel getById (
        @PathVariable Long itemId
    ) {
        return super.getById(itemId);
    }

    /**
     * Get Education Levels List
     *
     * @return Education Levels List
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Iterable<EducationLevel> getAll() {
        return super.getAll();
    }

    /**
     * Create new Education Level
     *
     * @param entityToCreate    create entity
     * @return New Education Level
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public EducationLevel create (
        @RequestBody EducationLevel entityToCreate
    ) {
        return super.create(entityToCreate);
    }

    /**
     * Update Education Level
     *
     * @param itemId    item to update identifier
     * @param entityToUpdateWith    update entity
     * @return Updated Education Level
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public EducationLevel update (
        @PathVariable Long itemId,
        @RequestBody EducationLevel entityToUpdateWith
    ) {
        return super.update(itemId, entityToUpdateWith);
    }

    /**
     * Deletes Education Level
     *
     * @param itemId    item to delete identifier
     * @return ID of removed Education Level
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
