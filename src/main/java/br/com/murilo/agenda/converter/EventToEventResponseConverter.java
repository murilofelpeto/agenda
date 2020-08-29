package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.response.EventResponse;
import br.com.murilo.agenda.dto.Response;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EventToEventResponseConverter implements Converter<Event, EventResponse> {

    @Override
    public EventResponse convert(final Event event) {

        return new EventResponse(event.getId(),
                event.getInitialDateTime(),
                event.getFinalDateTime(),
                event.getEventName(),
                event.getEventDescription(),
                event.getEventColor(),
                getOrganizerResponse(event),
                getGuestsResponse(event));
    }

    private Response getOrganizerResponse(final Event event){
        final var organizerEmail = event.getOrganizerEmail();
        return new Response(organizerEmail, EventResponseEnum.fromString(event.getOrganizerResponse().toString()));
    }

    private List<Response> getGuestsResponse(final Event event) {
        final var guests = event.getGuests();
        return guests
                .stream()
                .map(guest -> new Response(guest.getUser().getUsername(),
                              EventResponseEnum.fromString(guest.getResponse().toString())))
                .collect(Collectors.toList());
    }
}
