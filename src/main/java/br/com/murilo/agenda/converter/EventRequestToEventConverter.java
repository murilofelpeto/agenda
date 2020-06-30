package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.request.EventRequest;
import br.com.murilo.agenda.entity.*;
import br.com.murilo.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static br.com.murilo.agenda.types.EventResponseEnum.*;

@Service
public class EventRequestToEventConverter implements Converter<EventRequest, Event> {

    private final UserService userService;

    public EventRequestToEventConverter(@Autowired UserService userService) {
        this.userService = userService;
    }


    @Override
    public Event convert(final EventRequest eventRequest) {
        var organizer = userService.findByUsername(eventRequest.getOrganizerEmail());

        List<ApplicationUser> guests = userService.findUsersByUsername(eventRequest.getGuestsEmail());
        Set<EventUser> eventGuests = guests.stream()
                .map(guest -> {
                    return new EventUser(MAYBE, guest);
                })
                .collect(Collectors.toSet());

        final Event event = new Event(eventRequest.getId(),
                eventRequest.getInitialDateTime(),
                eventRequest.getFinalDateTime(),
                eventRequest.getEventColor(),
                Map.of(YES, organizer),
                eventGuests);
        return event;
    }
}
