package br.com.murilo.agenda.controller;

import br.com.murilo.agenda.dto.response.UserResponse;
import br.com.murilo.agenda.facade.UserFacade;
import br.com.murilo.agenda.service.UploadFilesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api("Manage users")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(final UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @ApiOperation("Upload an profile image")
    @PostMapping("/profilePicture")
    public UserResponse insertProfilePicture(@RequestParam("picture") MultipartFile picture) {
        final String username = getUsername();
        UserResponse user = userFacade.uploadImage(username, picture);
        return user;
    }

    private String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
