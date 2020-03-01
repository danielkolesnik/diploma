package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.controller.payload.SignUpUser;
import com.university.contractors.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(
        value = Endpoints.SIGN_UP,
        produces = MediaType.APPLICATION_JSON,
        name = "Sign Up Management Controller"
)
@Api(tags = "Sign Up Management")
public class SingUpController {

    private final UserService userService;

    @Autowired
    public SingUpController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Sign Up User
     *
     * @param userToSignUp
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "authorization", value = "oAuth Access token for API calls", defaultValue = "Bearer DF0310", required = true, dataType = "string", paramType = "header")
//    })
    public void singUpUser (
        @RequestBody SignUpUser userToSignUp
    ) {
        userService.signUpUser(userToSignUp);
    }

}
