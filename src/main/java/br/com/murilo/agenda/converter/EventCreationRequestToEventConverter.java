package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.request.EventCreationRequest;
import br.com.murilo.agenda.entity.*;
import br.com.murilo.agenda.service.UserService;
import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static br.com.murilo.agenda.types.EventResponseEnum.*;

@Service
public class EventCreationRequestToEventConverter implements Converter<EventCreationRequest, Event> {

    private final UserService userService;

    public EventCreationRequestToEventConverter(@Autowired UserService userService) {
        this.userService = userService;
    }


    @Override
    public Event convert(final EventCreationRequest eventCreationRequest) {
        final var organizer = userService.findByUsername(eventCreationRequest.getOrganizerEmail());
        List<ApplicationUser> users = userService.findUsersByUsername(eventCreationRequest.getGuestsEmail());

        List<EventUserResponse> guests = users.stream().map(user -> new EventUserResponse(user, MAYBE)).collect(Collectors.toList());

        return new Event(eventCreationRequest.getId(),
                eventCreationRequest.getInitialDateTime(),
                eventCreationRequest.getFinalDateTime(),
                eventCreationRequest.getEventName(),
                eventCreationRequest.getEventDescription(),
                eventCreationRequest.getEventColor(),
                new EventUserResponse(organizer, YES),
                guests);
    }
}
