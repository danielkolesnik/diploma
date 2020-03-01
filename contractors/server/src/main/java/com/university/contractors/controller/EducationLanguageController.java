package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.jpa.entity.EducationLanguage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(
        value = Endpoints.EDUCATION_LANGUAGE,
        produces = MediaType.APPLICATION_JSON,
        name = "Education Languages Management Controller"
)
@Api(tags = "Education Languages Management")
public class EducationLanguageController extends AbstractCrudControllerBase<Long, EducationLanguage> {

    @Autowired
    public EducationLanguageController(CrudRepository<EducationLanguage, Long> crudRepository) {
        super(crudRepository);
    }

    /**
     * Get Education Languages List
     *
     * @return Education Languages List
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
    })
    public Iterable<EducationLanguage> getAll() {
        return super.getAll();
    }
}