package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.response.UserResponse;
import br.com.murilo.agenda.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserToUserResponseConverter implements Converter<User, UserResponse> {

    @Override
    public UserResponse convert(final User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
