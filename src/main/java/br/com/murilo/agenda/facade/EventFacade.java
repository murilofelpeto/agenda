package br.com.murilo.agenda.facade;

import br.com.murilo.agenda.dto.request.EventRequest;
import br.com.murilo.agenda.dto.response.EventResponse;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.entity.EventUser;
import br.com.murilo.agenda.service.EventService;
import br.com.murilo.agenda.service.EventUserService;
import br.com.murilo.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventFacade {

    private EventService eventService;
    private ConversionService conversionService;
    private UserService userService;
    private EventUserService eventUserService;

    public EventFacade(@Autowired EventService eventService,
                       @Autowired ConversionService conversionService,
                       @Autowired UserService userService,
                       @Autowired EventUserService eventUserService) {
        this.eventService = eventService;
        this.conversionService = conversionService;
        this.userService = userService;
        this.eventUserService = eventUserService;
    }

    public EventResponse createEvent(EventRequest eventRequest){
        Event event = conversionService.convert(eventRequest, Event.class);
        event.setGuests(eventUserService.saveAll(event.getGuests()));
        event = eventService.createEvent(event);
        return conversionService.convert(event, EventResponse.class);
    }

    public List<EventResponse> findEventsBetweenDates(final LocalDateTime initialDate, final LocalDateTime endDate, String username) {
        final var userId = userService.findByUsername(username).getId();
        List<Event> events = eventService.findEventsByDateBetween(initialDate, endDate, userId);
        return events.stream()
                .map(event -> conversionService.convert(event,EventResponse.class))
                .collect(Collectors.toList());
    }
}
