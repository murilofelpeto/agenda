package br.com.murilo.agenda.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventResponse {

    private String id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime initialDateTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime finalDateTime;
    private String eventColor;
    private UserEventResponse organizer;
    private List<UserEventResponse> guests;

    public EventResponse(final String id,
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

    public LocalDateTime getInitialDateTime() {
        return initialDateTime;
    }

    public LocalDateTime getFinalDateTime() {
        return finalDateTime;
    }

    public String getEventColor() {
        return eventColor;
    }

    public UserEventResponse getOrganizer() {
        return organizer;
    }

    public List<UserEventResponse> getGuests() {
        return guests;
    }
}
