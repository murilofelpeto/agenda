package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.request.UserRequest;
import br.com.murilo.agenda.entity.ApplicationUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserRequestToUserConverter implements Converter<UserRequest, ApplicationUser> {

    @Override
    public ApplicationUser convert(final UserRequest userRequest) {
        final ApplicationUser user = new ApplicationUser();
        user.setId(userRequest.getId());
        user.setPassword(userRequest.getPassword());
        user.setUsername(userRequest.getEmail());
        return user;
    }
}
