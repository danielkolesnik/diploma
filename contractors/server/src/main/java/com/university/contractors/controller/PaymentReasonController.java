package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.PaymentReason;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(
        value = Endpoints.PAYMENT_REASONS,
        produces = MediaType.APPLICATION_JSON,
        name = "Payment Reasons Management Controller"
)
@Api(tags = "Payment Reasons Management")
public class PaymentReasonController extends AbstractCrudControllerBase<Long, PaymentReason> {

    public PaymentReasonController(CrudRepository<PaymentReason, Long> crudRepository) {
        super(crudRepository);
    }

    /**
     * Get Payment Reason Item
     *
     * @param itemId    item identifier
     * @return Payment Reason item
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public PaymentReason getById(
        @PathVariable Long itemId
    ) {
        return super.getById(itemId);
    }

    /**
     * Get Payment Reason List
     *
     * @return Payment Reason List
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Iterable<PaymentReason> getAll() {
        return super.getAll();
    }

    /**
     * Create new Payment Reason
     *
     * @param entityToCreate    create entity
     * @return New Payment Reason
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public PaymentReason create (
        @RequestBody PaymentReason entityToCreate
    ) {
        return super.create(entityToCreate);
    }

    /**
     * Update Payment Reason
     *
     * @param itemId    item to update identifier
     * @param entityToUpdateWith    update entity
     * @return Updated Payment Reason
     */
    @Override
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public PaymentReason update(
        @PathVariable Long itemId,
        @RequestBody PaymentReason entityToUpdateWith
    ) {
        return super.update(itemId, entityToUpdateWith);
    }

    /**
     * Deletes Payment Reason
     *
     * @param itemId    item to delete identifier
     * @return ID of removed Payment Reason
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
