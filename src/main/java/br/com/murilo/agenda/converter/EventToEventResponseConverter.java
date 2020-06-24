package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.response.EventResponse;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.entity.EventUser;
import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<EventResponseEnum, String> getOrganizerResponse(final Event event){
        final var organizer = event.getOrganizer();
        Map<EventResponseEnum, String> organizerResponse = new HashMap<>();
        organizerResponse.put(organizer.getResponse(), organizer.getUsername());
        return organizerResponse;
    }

    private Map<EventResponseEnum, List<String>> getGuestsResponse(final Event event) {
        final var guests = event.getGuests();
        return guests.stream().collect(groupingBy(EventUser::getResponse, mapping(EventUser::getUsername, toList())));
    }
}
