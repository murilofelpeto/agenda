package br.com.murilo.agenda.service;

import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.exception.ResourceAlreadyExistsException;
import br.com.murilo.agenda.exception.ResourceNotExistsException;
import br.com.murilo.agenda.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final static String EVENT_DOES_NOT_EXIST = "Evento não existe!";
    private final static String EVENT_ALREADY_EXIST = "Evento já existe!";

    private final EventRepository eventRepository;

    @Autowired
    public EventService(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(final Event event) {
        if(event.getId() == null) {
           return this.eventRepository.insert(event);
        }
        throw new ResourceAlreadyExistsException(EVENT_ALREADY_EXIST);
    }

    public Event updateEvent(final String eventID, final Event event) {
        if(eventExist(eventID)) {
            event.setId(eventID);
            return this.eventRepository.save(event);
        }
        throw new ResourceNotExistsException(EVENT_DOES_NOT_EXIST);
    }

    public void deleteEvent(final String eventID, final Event event) {
        if(eventExist(eventID)) {
            this.eventRepository.delete(event);
            return;
        }
        throw new ResourceNotExistsException(EVENT_DOES_NOT_EXIST);
    }

    public List<Event> findEvents(final String userID, final LocalDateTime initialDate, final LocalDateTime endDate) {
        final Set<Event> events = new HashSet<>();
        events.addAll(this.eventRepository.findByInitialDateTimeBetweenAndOrganizerUserId(initialDate, endDate, userID));
        events.addAll(this.eventRepository.findByInitialDateTimeBetweenAndGuestsUserId(initialDate, endDate, userID));
        return new ArrayList<>(events);
    }

    private Boolean eventExist(String id) {
        return this.eventRepository.findById(id).isPresent();
    }
}
