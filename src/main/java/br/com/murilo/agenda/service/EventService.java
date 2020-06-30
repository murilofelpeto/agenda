package br.com.murilo.agenda.service;

import br.com.murilo.agenda.entity.ApplicationUser;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.entity.EventUser;
import br.com.murilo.agenda.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static br.com.murilo.agenda.types.EventResponseEnum.YES;

@Service
public class EventService {

    private EventRepository eventRepository;

    public EventService(@Autowired EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findEventsByDateBetween(final LocalDateTime startDate,
                                               final LocalDateTime endDate,
                                               final String userID) {
        return eventRepository.findByOrganizer(YES, userID);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
}
