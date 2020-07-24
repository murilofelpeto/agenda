package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.request.EventRequest;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.entity.EventUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventRequestToEventConverter implements Converter<EventRequest, Event> {


    private final ConversionService conversionService;

    @Autowired
    public EventRequestToEventConverter(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Event convert(final EventRequest eventRequest) {
        EventUserResponse organizer = this.conversionService.convert(eventRequest.getOrganizer(), EventUserResponse.class);
        List<EventUserResponse> guests = eventRequest.getGuests().stream().map(guestResponse -> this.conversionService.convert(guestResponse, EventUserResponse.class)).collect(Collectors.toList());

        return new Event(eventRequest.getId(),
                eventRequest.getInitialDateTime(),
                eventRequest.getFinalDateTime(),
                eventRequest.getEventName(),
                eventRequest.getEventDescription(),
                eventRequest.getEventColor(),
                organizer,
                guests);
    }
}
