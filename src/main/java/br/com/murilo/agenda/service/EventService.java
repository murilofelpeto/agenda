package br.com.murilo.agenda.service;

import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        //TODO Criar exception
        throw new RuntimeException(EVENT_ALREADY_EXIST);
    }

    public Event updateEvent(final String id, final Event event) {
        if(eventExist(id)) {
            event.setId(id);
            return this.eventRepository.save(event);
        }
        throw new RuntimeException(EVENT_DOES_NOT_EXIST);
    }

    public void deleteEvent(final String id, final Event event) {
        if(eventExist(id)) {
            this.eventRepository.delete(event);
            return;
        }
        throw new RuntimeException(EVENT_DOES_NOT_EXIST);
    }

    public List<Event> findEvents(String id, final LocalDateTime initialDate, final LocalDateTime endDate) {
        return this.eventRepository.findByInitialDateTimeBetweenAndOrganizerUserId(initialDate, endDate, id);
    }

    private Boolean eventExist(String id) {
        return this.eventRepository.findById(id).isPresent();
    }
}
