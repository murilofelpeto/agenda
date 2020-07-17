package br.com.murilo.agenda.entity;

import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.*;

@Document(collection = "event")
public class Event {

    @Id
    private String id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime initialDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime finalDateTime;
    private String eventColor;


    @DBRef
    private Map<ApplicationUser, EventResponseEnum> organizer;

    @DBRef
    private Map<ApplicationUser, EventResponseEnum> guests;

    public Event(){
        this.organizer = new HashMap<>();
        this.guests = new HashMap<>();
    }

    public Event(final String id,
                 final LocalDateTime initialDateTime,
                 final LocalDateTime finalDateTime,
                 final String eventColor,
                 final Map<ApplicationUser, EventResponseEnum> organizer,
                 final Map<ApplicationUser, EventResponseEnum> guests) {

        this.organizer = new HashMap<>();
        this.guests = new HashMap<>();

        this.id = id;
        this.initialDateTime = initialDateTime;
        this.finalDateTime = finalDateTime;
        this.eventColor = eventColor;

        if(organizer.size() == 1) {
            this.organizer.putAll(organizer);
        }
        this.guests.putAll(guests);
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public LocalDateTime getInitialDateTime() {
        return initialDateTime;
    }

    public void setInitialDateTime(final LocalDateTime initialDateTime) {
        this.initialDateTime = initialDateTime;
    }

    public LocalDateTime getFinalDateTime() {
        return finalDateTime;
    }

    public void setFinalDateTime(final LocalDateTime finalDateTime) {
        this.finalDateTime = finalDateTime;
    }

    public String getEventColor() {
        return eventColor;
    }

    public void setEventColor(final String eventColor) {
        this.eventColor = eventColor;
    }

    public Map<ApplicationUser, EventResponseEnum> getOrganizer() {
        return organizer;
    }

    public void addOrganizer(final ApplicationUser organizer, final EventResponseEnum response) {
        if(this.organizer.isEmpty()) {
            this.organizer.put(organizer, response);
        }
        //TODO Criar exception
        throw new RuntimeException("Só pode ter um organizador neste evento");
    }

    public void updateOrganizerResponse(final ApplicationUser organizer, final EventResponseEnum response) {
        this.organizer.replace(organizer, response);
    }

    public Map<ApplicationUser, EventResponseEnum> getGuests() {
        return guests;
    }

    public void addGuests(final ApplicationUser guest, EventResponseEnum response) {
        this.guests.put(guest, response);
    }

    public void deleteGuests(final ApplicationUser guest) {
        this.guests.remove(guest);
    }

    public void updateGuestResponse(final ApplicationUser guest, EventResponseEnum response) {
        this.guests.replace(guest, response);
    }
}
