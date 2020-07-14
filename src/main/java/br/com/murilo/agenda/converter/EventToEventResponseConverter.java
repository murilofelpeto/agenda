package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.response.EventResponse;
import br.com.murilo.agenda.dto.response.Response;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.entity.EventUser;
import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class EventToEventResponseConverter implements Converter<Event, EventResponse> {

    @Override
    public EventResponse convert(final Event event) {

        return new EventResponse(event.getId(),
                event.getInitialDateTime(),
                event.getFinalDateTime(),
                event.getEventColor(),
                getOrganizerResponse(event),
                getGuestsResponse(event));
    }

    private Response getOrganizerResponse(final Event event){
        final var organizer = event.getOrganizer();
        Object response = organizer.entrySet().stream().map(Map.Entry::getKey).findFirst().get();
        final var organizerEmail = organizer.values().stream().findFirst().get().getUsername();
        return new Response(EventResponseEnum.fromString(response.toString()), organizerEmail);
    }

    private List<Response> getGuestsResponse(final Event event) {
        final var guests = event.getGuests();
        return guests.stream().map(guest -> new Response(guest.getResponse(), guest.getUsername())).collect(Collectors.toList());
    }
}
