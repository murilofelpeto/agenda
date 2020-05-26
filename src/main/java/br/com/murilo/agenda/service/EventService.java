package br.com.murilo.agenda.service;

import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    public EventService(@Autowired EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findEventsByDateBetween(final LocalDateTime startDate,
                                               final LocalDateTime endDate,
                                               final String userID) {
        return eventRepository.findEventsBetweenStartDateAndEndDate(startDate, endDate, userID);
    }

    public Event createEvent(Event event) {
        return eventRepository.insert(event);
    }
}
