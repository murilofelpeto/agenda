package br.com.murilo.agenda.facade;

import br.com.murilo.agenda.dto.request.UserRequest;
import br.com.murilo.agenda.dto.response.UserResponse;
import br.com.murilo.agenda.entity.ApplicationUser;
import br.com.murilo.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    private UserService userService;
    private ConversionService conversionService;

    public UserFacade(@Autowired UserService userService,
                      @Autowired ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    public UserResponse createUser(final UserRequest request) {
        ApplicationUser user = conversionService.convert(request, ApplicationUser.class);
        user = userService.createUser(user);
        return conversionService.convert(user, UserResponse.class);
    }
}
