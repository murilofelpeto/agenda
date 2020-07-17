package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.response.UserEventResponse;
import br.com.murilo.agenda.entity.ApplicationUser;
import br.com.murilo.agenda.service.UserService;
import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserEventResponseToMapConverter implements Converter<UserEventResponse, Map> {

    private final UserService userService;

    @Autowired
    public UserEventResponseToMapConverter(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public Map<ApplicationUser, EventResponseEnum> convert(final UserEventResponse userEventResponse) {
        Map<ApplicationUser, EventResponseEnum> entity = new HashMap<>();
        ApplicationUser user = this.userService.findByUsername(userEventResponse.getUsername());
        entity.put(user, userEventResponse.getResponse());
        return entity;
    }
}
