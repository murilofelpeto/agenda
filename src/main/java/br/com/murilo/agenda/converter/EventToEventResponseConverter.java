package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.response.EventResponse;
import br.com.murilo.agenda.entity.Event;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class EventToEventResponseConverter implements Converter<Event, EventResponse> {

    @Override
    public EventResponse convert(final Event event) {

        return new EventResponse(event.getId(),
                event.getInitialDateTime(),
                event.getFinalDateTime(),
                event.getEventColor(),
                event.getOrganizerEmail(),
                event.getGuestsEmail());
    }
}
