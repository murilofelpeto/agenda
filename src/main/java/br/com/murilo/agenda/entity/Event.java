package br.com.murilo.agenda.entity;

import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.*;

@Document
public class Event {

    @Id
    private String id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime initialDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime finalDateTime;
    private String eventColor;


    @DBRef
    private EventUser organizer;

    @DBRef
    private Set<EventUser> guests;

    public Event(){
        this.guests = new HashSet<>();
    }

    public Event(final String id,
                 final LocalDateTime initialDateTime,
                 final LocalDateTime finalDateTime,
                 final String eventColor,
                 final EventUser organizer,
                 final Set<EventUser> guests) {
        this.id = id;
        this.initialDateTime = initialDateTime;
        this.finalDateTime = finalDateTime;
        this.eventColor = eventColor;
        this.organizer = organizer;
        this.guests = new HashSet<>();
        this.guests.addAll(guests);
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

    public EventUser getOrganizer() {
        return organizer;
    }

    public void setOrganizer(final EventUser organizer) {
        this.organizer = organizer;
    }

    public Set<EventUser> getGuests() {
        return guests;
    }

    public void addGuest(EventUser guest) {
        this.guests.add(guest);
    }

    public void setGuests(final Set<EventUser> guests) {
        this.guests = guests;
    }

    public void updateGuest(EventUser guest) {
        if(this.guests.contains(guest)) {
            this.guests.remove(guest);
            this.guests.add(guest);
        }
    }

    public void removeGuest(EventUser guest) {
        this.guests.removeIf(g -> g.equals(guest));
    }


}
