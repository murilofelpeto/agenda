package br.com.murilo.agenda.facade;

import br.com.murilo.agenda.dto.request.UserRequest;
import br.com.murilo.agenda.dto.response.UserResponse;
import br.com.murilo.agenda.entity.Role;
import br.com.murilo.agenda.entity.User;
import br.com.murilo.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private ConversionService conversionService;

    public UserResponse createUser(final UserRequest request) {
        User user = conversionService.convert(request, User.class);
        user = userService.createUser(user);
        return conversionService.convert(user, UserResponse.class);
    }
}
