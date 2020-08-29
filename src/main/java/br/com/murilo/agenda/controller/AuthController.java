package br.com.murilo.agenda.controller;

import br.com.murilo.agenda.dto.request.UserRequest;
import br.com.murilo.agenda.dto.response.UserResponse;
import br.com.murilo.agenda.facade.UserFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api("Manage login and logout for users")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserFacade userFacade;
    private final BCryptPasswordEncoder encoder;

    public AuthController(@Autowired UserFacade userFacade,
                          @Autowired BCryptPasswordEncoder encoder) {
        this.userFacade = userFacade;
        this.encoder = encoder;
    }

    @ApiOperation("Register a new user!")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        request.setPassword(encoder.encode(request.getPassword()));
        UserResponse response = userFacade.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
