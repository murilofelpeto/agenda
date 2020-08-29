package br.com.murilo.agenda.facade;

import br.com.murilo.agenda.dto.request.EventCreationRequest;
import br.com.murilo.agenda.dto.request.EventRequest;
import br.com.murilo.agenda.dto.request.UpdateResponse;
import br.com.murilo.agenda.dto.response.EventResponse;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.service.EventService;

import br.com.murilo.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventFacade {

    private final EventService eventService;
    private final UserService userService;
    private final ConversionService conversionService;

    @Autowired
    public EventFacade(final EventService eventService,
                       final ConversionService conversionService,
                       final UserService userService) {
        this.eventService = eventService;
        this.conversionService = conversionService;
        this.userService = userService;
    }

    public EventResponse createEvent(final EventCreationRequest request) {
        final Event event = this.conversionService.convert(request, Event.class);
        final Event createdEvent = this.eventService.createEvent(event);
        return this.conversionService.convert(createdEvent, EventResponse.class);
    }

    public EventResponse updateEvent(final String eventID, final EventRequest request, final String username) {
        final String userID = getUserID(username);
        final Event event = this.conversionService.convert(request, Event.class);
        final Event updatedEvent = this.eventService.updateEvent(eventID, event,userID);
        return this.conversionService.convert(updatedEvent, EventResponse.class);
    }

    public List<EventResponse> findEvents(final String username, final LocalDateTime initialDate, final LocalDateTime endDate) {
        final String userID = getUserID(username);
        final List<Event> events = this.eventService.findEvents(userID, initialDate, endDate);
        return events.stream().map(event -> this.conversionService.convert(event, EventResponse.class)).collect(Collectors.toList());
    }

    public void deleteEvent(final String eventID, final EventRequest eventRequest, final String username) {
        final String userID = getUserID(username);
        final Event event = this.conversionService.convert(eventRequest, Event.class);
        this.eventService.deleteEvent(eventID, event, userID);
    }

    public EventResponse findEventById(final String id) {
        final Event event = this.eventService.findEventById(id);
        return this.conversionService.convert(event, EventResponse.class);
    }

    private String getUserID(String username) {
        return this.userService.findByUsername(username).getId();
    }

    public EventResponse updateEventResponse(final String username, final UpdateResponse updateResponse, final String id) {
        final Event updatedEvent = this.eventService.updateEventResponse(username, updateResponse, id);
        return this.conversionService.convert(updatedEvent, EventResponse.class);
    }
}
