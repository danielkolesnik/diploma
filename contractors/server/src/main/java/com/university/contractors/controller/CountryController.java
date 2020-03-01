package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Country;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(
        value = Endpoints.COUNTRIES,
        produces = MediaType.APPLICATION_JSON,
        name = "Countries Management Controller"
)
@Api(tags = "Countries Management")
public class CountryController extends AbstractCrudControllerBase<Long, Country> {

    public CountryController(@Qualifier("countryRepository") CrudRepository<Country, Long> crudRepository) {
        super(crudRepository);
    }

    /**
     * Get Country Item
     *
     * @param itemId    item identifier
     * @return Country item
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Country getById (
        @PathVariable Long itemId
    ) {
        return super.getById(itemId);
    }

    /**
     * Get Country List
     *
     * @return Country List
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Iterable<Country> getAll() {
        return super.getAll();
    }

    /**
     * Create new Country
     *
     * @param entityToCreate    create entity
     * @return New Country
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Country create (
        @RequestBody Country entityToCreate
    ) {
        return super.create(entityToCreate);
    }

    /**
     * Update Country
     *
     * @param itemId    item to update identifier
     * @param entityToUpdateWith    update entity
     * @return Updated Country
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Country update(
        @PathVariable Long itemId,
        @RequestBody Country entityToUpdateWith
    ) {
        return super.update(itemId, entityToUpdateWith);
    }

    /**
     * Deletes Country
     *
     * @param itemId    item to delete identifier
     * @return ID of removed Country
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Long delete(
        @PathVariable Long itemId
    ) {
        return super.delete(itemId);
    }
}
