package br.com.murilo.agenda.facade;

import br.com.murilo.agenda.dto.request.UserRequest;
import br.com.murilo.agenda.dto.response.UserResponse;
import br.com.murilo.agenda.entity.ApplicationUser;
import br.com.murilo.agenda.entity.Picture;
import br.com.murilo.agenda.service.UploadFilesService;
import br.com.murilo.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class UserFacade {

    private final UserService userService;
    private final UploadFilesService uploadFilesService;
    private final ConversionService conversionService;

    @Autowired
    public UserFacade(final UserService userService,
                      final UploadFilesService uploadFilesService,
                      final ConversionService conversionService) {

        this.userService = userService;
        this.uploadFilesService = uploadFilesService;
        this.conversionService = conversionService;
    }

    public UserResponse createUser(final UserRequest request) {
        ApplicationUser user = conversionService.convert(request, ApplicationUser.class);
        user = userService.createUser(user);
        return conversionService.convert(user, UserResponse.class);
    }

    public UserResponse uploadImage(final String username, final MultipartFile file) {
        ApplicationUser user = this.userService.findByUsername(username);
        Picture picture = this.conversionService.convert(file, Picture.class);
        user.setProfilePicture(picture);
        user = this.userService.update(user.getId(), user);
        return this.conversionService.convert(user, UserResponse.class);
    }
}
