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
    private String organizerEmail;
    private List<String> guestsEmail;

    public EventResponse(){}

    public EventResponse(final String id,
                         final LocalDateTime initialDateTime,
                         final LocalDateTime finalDateTime,
                         final String eventColor,
                         final String organizerEmail,
                         final List<String> guestsEmail) {
        this.id = id;
        this.initialDateTime = initialDateTime;
        this.finalDateTime = finalDateTime;
        this.eventColor = eventColor;
        this.organizerEmail = organizerEmail;
        this.guestsEmail = new ArrayList<>();
        this.guestsEmail.addAll(guestsEmail);
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

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public List<String> getGuestsEmail() {
        return guestsEmail;
    }
}
