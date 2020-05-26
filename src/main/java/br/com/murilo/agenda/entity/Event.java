package br.com.murilo.agenda.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document
public class Event {

    @Id
    private String id;
    private LocalDateTime initialDateTime;
    private LocalDateTime finalDateTime;
    private String eventColor;

    @DBRef
    private Organizer organizer;

    @DBRef
    private List<Guest> guests;

    public Event(final String id,
                 final LocalDateTime initialDateTime,
                 final LocalDateTime finalDateTime,
                 final String eventColor,
                 final Organizer organizer,
                 final List<Guest> guests) {
        this.id = id;
        this.initialDateTime = initialDateTime;
        this.finalDateTime = finalDateTime;
        this.eventColor = eventColor;
        this.organizer = organizer;
        this.guests = new ArrayList<>();
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

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(final Organizer organizer) {
        this.organizer = organizer;
    }

    public String getOrganizerEmail() {
        return this.organizer.getUsername();
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(final List<Guest> guests) {
        this.guests = guests;
    }

    public List<String> getGuestsEmail() {
        return this.guests.stream()
                .map(Guest::getUsername)
                .collect(Collectors.toList());
    }

    public void addGuest(Guest guest) {
        this.guests.add(guest);
    }

    public void removeGuest(Guest guest) {
        this.guests.remove(guest);
    }
}
