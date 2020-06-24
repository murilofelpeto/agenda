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

@Service
public class EventService {

    private EventRepository eventRepository;

    public EventService(@Autowired EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findEventsByDateBetween(final LocalDateTime startDate,
                                               final LocalDateTime endDate,
                                               final String userID) {
        Event event = new Event();
        ApplicationUser user = new ApplicationUser();
        user.setId(userID);
        EventUser organizer = new EventUser();
        organizer.setUser(user);
        event.setOrganizer(organizer);

        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreNullValues();
        Example<Event> example = Example.of(event, matcher);
        return eventRepository.findAll(example);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
}
