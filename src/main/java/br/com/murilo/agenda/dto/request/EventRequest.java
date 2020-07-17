package br.com.murilo.agenda.dto.request;

import br.com.murilo.agenda.dto.response.UserEventResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventRequest {

    private String id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime initialDateTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime finalDateTime;
    private String eventColor;
    private UserEventResponse organizer;
    private List<UserEventResponse> guests;

    public EventRequest(final String id,
                        final LocalDateTime initialDateTime,
                        final LocalDateTime finalDateTime,
                        final String eventColor,
                        final UserEventResponse organizer,
                        final List<UserEventResponse> guests) {
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

    public UserEventResponse getOrganizer() {
        return organizer;
    }

    public void setOrganizer(final UserEventResponse organizer) {
        this.organizer = organizer;
    }

    public List<UserEventResponse> getGuests() {
        return guests;
    }

    public void setGuests(final List<UserEventResponse> guests) {
        this.guests = guests;
    }
}
