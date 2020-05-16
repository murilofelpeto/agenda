package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.response.UserResponse;
import br.com.murilo.agenda.entity.ApplicationUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserToUserResponseConverter implements Converter<ApplicationUser, UserResponse> {

    @Override
    public UserResponse convert(final ApplicationUser user) {
        return new UserResponse(user.getId(), user.getUsername());
    }
}
