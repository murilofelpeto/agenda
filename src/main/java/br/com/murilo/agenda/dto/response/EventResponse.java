package br.com.murilo.agenda.dto.response;

import br.com.murilo.agenda.dto.Response;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventResponse {

    private final String id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private final LocalDateTime initialDateTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private final LocalDateTime finalDateTime;
    private final String eventName;
    private final String eventDescription;
    private final String eventColor;
    private final Response organizer;
    private final List<Response> guests;

    public EventResponse(final String id,
                         final LocalDateTime initialDateTime,
                         final LocalDateTime finalDateTime,
                         final String eventName,
                         final String eventDescription,
                         final String eventColor,
                         final Response organizer,
                         final List<Response> guests) {
        this.id = id;
        this.initialDateTime = initialDateTime;
        this.finalDateTime = finalDateTime;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
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

    public Response getOrganizer() {
        return organizer;
    }

    public List<Response> getGuests() {
        return guests;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }
}
