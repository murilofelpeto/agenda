package br.com.murilo.agenda.service;

import br.com.murilo.agenda.dto.request.UpdateResponse;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.exception.EventNotModifiedException;
import br.com.murilo.agenda.exception.ResourceAlreadyExistsException;
import br.com.murilo.agenda.exception.ResourceNotExistsException;
import br.com.murilo.agenda.repository.EventRepository;
import br.com.murilo.agenda.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EventService {

    private final static String EVENT_DOES_NOT_EXIST = "Evento não existe!";
    private final static String EVENT_ALREADY_EXIST = "Evento já existe!";
    public static final String CREATED_EVENT_SUBJECT = "Voce foi convidado para o evento!";
    private static final String USER_DOES_NOT_HAVE_PERMISSION = "O usuário não está participando deste evento";

    private final EventRepository eventRepository;
    private final EmailService emailService;

    @Autowired
    public EventService(final EventRepository eventRepository, final EmailService emailService) {
        this.eventRepository = eventRepository;
        this.emailService = emailService;
    }

    public Event createEvent(final Event event) {
        if(event.getId() == null) {
           final Event eventCreated = this.eventRepository.insert(event);
           emailService.sendEventNotification(eventCreated, CREATED_EVENT_SUBJECT);
           return eventCreated;
        }
        throw new ResourceAlreadyExistsException(EVENT_ALREADY_EXIST);
    }

    public Event updateEvent(final String eventID, final Event event, final String userID) {
        if(eventExist(eventID) && canModifyEvent(event, userID)) {
            event.setId(eventID);
            return this.eventRepository.save(event);
        }
        throw new ResourceNotExistsException(EVENT_DOES_NOT_EXIST);
    }

    public void deleteEvent(final String eventID, final Event event, final String userID) {
        if(eventExist(eventID) && canModifyEvent(event, userID)) {
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

    public Event findEventById(final String id) {
        final var optionalEvent = this.eventRepository.findById(id);
        if(optionalEvent.isPresent()){
            return optionalEvent.get();
        }
        throw new ResourceNotExistsException(EVENT_DOES_NOT_EXIST);
    }

    public Event updateEventResponse(final String username, final UpdateResponse updateResponse, final String id) {
        final var optionalEvent = this.eventRepository.findById(id);
        if(optionalEvent.isPresent()) {
            final Event event = optionalEvent.get();

            if(event.getOrganizerEmail().equals(username)) {
                event.setOrganizerResponse(updateResponse.getResponse());
                return this.eventRepository.save(event);
            }

            if(event.getGuestsEmails().contains(username)) {
                final var optionalGuest = event.getGuests().stream().filter(guest -> guest.getUsername().equals(username)).findFirst();
                if(optionalGuest.isPresent()) {
                    final var guest = optionalGuest.get();
                    guest.setResponse(updateResponse.getResponse());
                    event.updateGuests(guest);
                    return this.eventRepository.save(event);
                }
            }
            throw new EventNotModifiedException(USER_DOES_NOT_HAVE_PERMISSION);
        }
        throw new ResourceNotExistsException(EVENT_DOES_NOT_EXIST);
    }

    private Boolean eventExist(String id) {
        return this.eventRepository.findById(id).isPresent();
    }

    private Boolean canModifyEvent(final Event event, final String userID) {
        return event.getOrganizer().getUser().getId().equals(userID);
    }
}
