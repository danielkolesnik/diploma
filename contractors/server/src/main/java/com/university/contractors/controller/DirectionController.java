package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.jpa.entity.Direction;
import com.university.contractors.repository.DirectionRepository;
import com.university.contractors.repository.FacultyRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.core.MediaType;
import java.text.MessageFormat;

@RestController
@RequestMapping(
        value = Endpoints.DIRECTIONS,
        produces = MediaType.APPLICATION_JSON,
        name = "Directions Management Controller"
)
@Api(tags = "Directions Management")
public class DirectionController extends AbstractCrudControllerBase<Long, Direction> {

    private final DirectionRepository directionRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public DirectionController(DirectionRepository directionRepository, FacultyRepository facultyRepository) {
        super(directionRepository);
        this.directionRepository = directionRepository;
        this.facultyRepository = facultyRepository;
    }

    /**
     * Get Direction Item
     *
     * @param itemId    item identifier
     * @return Direction item
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Direction getById(
        @PathVariable Long itemId
    ) {
        return super.getById(itemId);
    }

    /**
     * Get Directions List
     *
     * @return Directions List
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Iterable<Direction> getAll() {
        return super.getAll();
    }

    /**
     * Get Directions List filtered
     *
     * @return Directions List
     */
    @RequestMapping(value = "", method = RequestMethod.GET, params = {"faculty_id"})
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Iterable<Direction> getDirectionByFacultyId (
        @RequestParam(name = "faculty_id") Long facultyId
    ) {
        if (facultyRepository.existsById(facultyId)) {
            return directionRepository.findByFacultyId(facultyId);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, MessageFormat.format("Faculty with ID '{0}' was not found", facultyId));
    }

    /**
     * Create new Direction
     *
     * @param entityToCreate    create entity
     * @return New Direction
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Direction create (
        @RequestBody Direction entityToCreate
    ) {
        return super.create(entityToCreate);
    }

    /**
     * Update Direction
     *
     * @param itemId    item to update identifier
     * @param entityToUpdateWith    update entity
     * @return Updated Direction
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Direction update (
        @PathVariable Long itemId,
        @RequestBody Direction entityToUpdateWith
    ) {
        return super.update(itemId, entityToUpdateWith);
    }

    /**
     * Deletes Direction
     *
     * @param itemId    item to delete identifier
     * @return ID of removed Direction
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
