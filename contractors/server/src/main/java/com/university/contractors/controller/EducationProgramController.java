package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.jpa.entity.EducationProgram;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(
        value = Endpoints.EDUCATION_PROGRAMS,
        produces = MediaType.APPLICATION_JSON,
        name = "Education Programs Management Controller"
)
@Api(tags = "Education Programs Management")
public class EducationProgramController extends AbstractCrudControllerBase<Long, EducationProgram> {

    public EducationProgramController(CrudRepository<EducationProgram, Long> crudRepository) {
        super(crudRepository);
    }

    /**
     * Get Education Program Item
     *
     * @param itemId    item identifier
     * @return Education Program item
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public EducationProgram getById(
        @PathVariable Long itemId
    ) {
        return super.getById(itemId);
    }

    /**
     * Get Education Programs List
     *
     * @return Education Programs List
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Iterable<EducationProgram> getAll() {
        return super.getAll();
    }

    /**
     * Create new Education Program
     *
     * @param entityToCreate    create entity
     * @return New Education Program
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public EducationProgram create (
        @RequestBody EducationProgram entityToCreate
    ) {
        return super.create(entityToCreate);
    }

    /**
     * Update Education Program
     *
     * @param itemId    item to update identifier
     * @param entityToUpdateWith    update entity
     * @return Updated Education Program
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public EducationProgram update (
        @PathVariable Long itemId,
        @RequestBody EducationProgram entityToUpdateWith
    ) {
        return super.update(itemId, entityToUpdateWith);
    }

    /**
     * Deletes Education Program
     *
     * @param itemId    item to delete identifier
     * @return ID of removed Education Program
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
