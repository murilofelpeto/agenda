package br.com.murilo.agenda.facade;

import br.com.murilo.agenda.dto.request.EventCreationRequest;
import br.com.murilo.agenda.dto.request.EventRequest;
import br.com.murilo.agenda.dto.response.EventResponse;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class EventFacade {

    private final EventService eventService;
    private final ConversionService conversionService;

    @Autowired
    public EventFacade(final EventService eventService, final ConversionService conversionService) {
        this.eventService = eventService;
        this.conversionService = conversionService;
    }

    public EventResponse createEvent(final EventCreationRequest request) {
        final Event event = this.conversionService.convert(request, Event.class);
        final Event createdEvent = this.eventService.createEvent(event);
        return this.conversionService.convert(createdEvent, EventResponse.class);
    }

    public EventResponse updateEvent(final String id, final EventRequest request) {
        final Event event = this.conversionService.convert(request, Event.class);
        final Event updatedEvent = this.eventService.updateEvent(id, event);
        return this.conversionService.convert(updatedEvent, EventResponse.class);
    }
}
