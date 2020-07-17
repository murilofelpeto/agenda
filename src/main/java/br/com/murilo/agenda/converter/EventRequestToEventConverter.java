package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.request.EventRequest;
import br.com.murilo.agenda.entity.ApplicationUser;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EventRequestToEventConverter implements Converter<EventRequest, Event> {


    private final ConversionService conversionService;

    @Autowired
    public EventRequestToEventConverter(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Event convert(final EventRequest eventRequest) {
        Map<ApplicationUser, EventResponseEnum> organizer = this.conversionService.convert(eventRequest.getOrganizer(), Map.class);
        Map<ApplicationUser, EventResponseEnum> guests = new HashMap<>();

        eventRequest.getGuests().forEach(guest -> {
            guests.putAll(this.conversionService.convert(guest, Map.class));
        });

        return new Event(eventRequest.getId(),
                eventRequest.getInitialDateTime(),
                eventRequest.getFinalDateTime(),
                eventRequest.getEventColor(),
                organizer,
                guests);
    }
}
