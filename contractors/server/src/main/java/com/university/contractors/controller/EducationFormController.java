package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.jpa.entity.EducationForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(
        value = Endpoints.EDUCATION_FORMS,
        produces = MediaType.APPLICATION_JSON,
        name = "Education Form Management Controller"
)
@Api(tags = "Education Form Management")
public class EducationFormController extends AbstractCrudControllerBase<Long, EducationForm> {

    public EducationFormController(CrudRepository<EducationForm, Long> crudRepository) {
        super(crudRepository);
    }

    /**
     * Get Education Form Item
     *
     * @param itemId    item identifier
     * @return Education Form item
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public EducationForm getById (
        @PathVariable Long itemId
    ) {
        return super.getById(itemId);
    }

    /**
     * Get Education Forms List
     *
     * @return Education Forms List
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Iterable<EducationForm> getAll() {
        return super.getAll();
    }


    /**
     * Create new Education Form
     *
     * @param entityToCreate    create entity
     * @return New Education Form
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public EducationForm create (
        @RequestBody EducationForm entityToCreate
    ) {
        return super.create(entityToCreate);
    }

    /**
     * Update Education Form
     *
     * @param itemId    item to update identifier
     * @param entityToUpdateWith    update entity
     * @return Updated Education Form
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public EducationForm update (
        @PathVariable Long itemId,
        @RequestBody EducationForm entityToUpdateWith
    ) {
        return super.update(itemId, entityToUpdateWith);
    }

    /**
     * Deletes Education Form
     *
     * @param itemId    item to delete identifier
     * @return ID of removed Education Form
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
