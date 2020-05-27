package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.request.EventRequest;
import br.com.murilo.agenda.entity.ApplicationUser;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.entity.Guest;
import br.com.murilo.agenda.entity.Organizer;
import br.com.murilo.agenda.service.UserService;
import static br.com.murilo.agenda.types.EventResponseEnum.MAYBE;
import static br.com.murilo.agenda.types.EventResponseEnum.YES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventRequestToEventConverter implements Converter<EventRequest, Event> {

    private final UserService userService;

    public EventRequestToEventConverter(@Autowired UserService userService) {
        this.userService = userService;
    }


    @Override
    public Event convert(final EventRequest eventRequest) {
        ApplicationUser organizerUser = userService.findByUsername(eventRequest.getOrganizerEmail());
        Organizer organizer = new Organizer(organizerUser.getId(),
                organizerUser.getName(),
                organizerUser.getUsername(),
                YES);

        List<ApplicationUser> guestUsers = userService.findUsersByUsername(eventRequest.getGuestsEmail());
        List<Guest> guests = guestUsers
                .stream()
                .map(guestUser -> new Guest(guestUser.getId(), guestUser.getName(), guestUser.getUsername(), MAYBE))
                .collect(Collectors.toList());

        final Event event = new Event(eventRequest.getId(),
                eventRequest.getInitialDateTime(),
                eventRequest.getFinalDateTime(),
                eventRequest.getEventColor(),
                organizer,
                guests);
        return event;
    }
}
