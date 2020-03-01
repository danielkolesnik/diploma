package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.ArrivalLine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.repository.CrudRepository;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
    value = Endpoints.ARRIVAL_LINES,
    produces = MediaType.APPLICATION_JSON,
    name = "Arrival Lines Management Controller"
)
@Api(tags = "Arrival Lines Management")
public class ArrivalLineController extends AbstractCrudControllerBase<Long, ArrivalLine> {

    public ArrivalLineController(CrudRepository<ArrivalLine, Long> crudRepository) {
        super(crudRepository);
    }

    /**
     * Get Arrival Line Item
     *
     * @param itemId    item identifier
     * @return Arrival Line item
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public ArrivalLine getById (
        @PathVariable Long itemId
    ) {
        return super.getById(itemId);
    }

    /**
     * Get Arrival Lines List
     *
     * @return Arrival Lines List
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Iterable<ArrivalLine> getAll() {
        return super.getAll();
    }

    /**
     * Create new Arrival Line
     *
     * @param entityToCreate    create entity
     * @return New Arrival Line
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public ArrivalLine create (
        @RequestBody ArrivalLine entityToCreate
    ) {
        return super.create(entityToCreate);
    }

    /**
     * Update Arrival Line
     *
     * @param itemId    item to update identifier
     * @param entityToUpdateWith    update entity
     * @return Updated Arrival Line
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public ArrivalLine update (
        @PathVariable Long itemId,
        @RequestBody ArrivalLine entityToUpdateWith
    ) {
        return super.update(itemId, entityToUpdateWith);
    }

    /**
     * Deletes Arrival Line
     *
     * @param itemId    item to delete identifier
     * @return ID of removed Arrival Line
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
