package br.com.murilo.agenda.facade;

import br.com.murilo.agenda.dto.request.EventRequest;
import br.com.murilo.agenda.dto.response.EventResponse;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.service.EventService;
import br.com.murilo.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class EventFacade {

    private EventService eventService;
    private ConversionService conversionService;

    public EventFacade(@Autowired EventService eventService,
                      @Autowired ConversionService conversionService) {
        this.eventService = eventService;
        this.conversionService = conversionService;
    }

    public EventResponse createEvent(EventRequest eventRequest){
        Event event = conversionService.convert(eventRequest, Event.class);
        event = eventService.createEvent(event);
        return conversionService.convert(event, EventResponse.class);
    }
}
