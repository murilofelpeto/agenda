package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.response.EventResponse;
import br.com.murilo.agenda.dto.response.UserEventResponse;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    private UserEventResponse getOrganizerResponse(final Event event){
        final var organizer = event.getOrganizer();
        final var response = organizer.entrySet().stream().map(Map.Entry::getValue).findFirst().get();
        final var organizerEmail = organizer.keySet().stream().findFirst().get().getUsername();
        return new UserEventResponse(organizerEmail, EventResponseEnum.fromString(response.toString()));
    }

    private List<UserEventResponse> getGuestsResponse(final Event event) {
        final var guests = event.getGuests();
        return guests.entrySet()
                .stream()
                .map(guest -> new UserEventResponse(guest.getKey().getUsername(),
                              EventResponseEnum.fromString(guest.getValue().toString())))
                .collect(Collectors.toList());
    }
}
