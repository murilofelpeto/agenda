package br.com.murilo.agenda.entity;

import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Document(collection = "event")
public class Event {

    @Id
    private String id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime initialDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime finalDateTime;
    private String eventName;
    private String eventDescription;
    private String eventColor;
    private EventUserResponse organizer;
    private List<EventUserResponse> guests;

    public Event(final String id,
                 final LocalDateTime initialDateTime,
                 final LocalDateTime finalDateTime,
                 final String eventName,
                 final String eventDescription,
                 final String eventColor,
                 final EventUserResponse organizer,
                 final List<EventUserResponse> guests) {

        this.id = id;
        this.initialDateTime = initialDateTime;
        this.finalDateTime = finalDateTime;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventColor = eventColor;
        this.organizer = organizer;
        this.guests = guests;
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

    public EventUserResponse getOrganizer() {
        return organizer;
    }

    public void setOrganizer(final EventUserResponse organizer) {
        this.organizer = organizer;
    }

    public String getOrganizerEmail() {
        return this.organizer.getUsername();
    }

    public EventResponseEnum getOrganizerResponse() {
        return this.organizer.getResponse();
    }

    public List<EventUserResponse> getGuests() {
        return guests;
    }

    public void setGuests(final List<EventUserResponse> guests) {
        this.guests = guests;
    }

    public List<String> getGuestsEmails() {
        return this.guests.stream().map(EventUserResponse::getUsername).collect(Collectors.toList());
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(final String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(final String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        final Event event = (Event) o;
        return getId().equals(event.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
