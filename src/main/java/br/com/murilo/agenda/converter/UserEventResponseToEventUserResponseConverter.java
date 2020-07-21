package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.Response;
import br.com.murilo.agenda.entity.ApplicationUser;
import br.com.murilo.agenda.entity.EventUserResponse;
import br.com.murilo.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserEventResponseToEventUserResponseConverter implements Converter<Response, EventUserResponse> {

    private final UserService userService;

    @Autowired
    public UserEventResponseToEventUserResponseConverter(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public EventUserResponse convert(final Response response) {
        ApplicationUser user = this.userService.findByUsername(response.getUsername());
        return new EventUserResponse(user, response.getResponse());
    }
}
