package br.com.murilo.agenda.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventCreationRequest {

    private String id;

    @NotNull(message = "{event.initial.date.not.null}")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime initialDateTime;

    @NotNull(message = "{event.final.date.not.null}")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime finalDateTime;

    @NotNull(message = "{event.name.not.null}")
    private String eventName;
    private String eventDescription;
    private String eventColor;
    private String organizerEmail;

    @NotEmpty(message = "{event.guests.not.empty}")
    private List<String> guestsEmail;

    public EventCreationRequest(){}

    public EventCreationRequest(final String id,
                                final LocalDateTime initialDateTime,
                                final LocalDateTime finalDateTime,
                                final String eventName,
                                final String eventDescription,
                                final String eventColor,
                                final String organizerEmail,
                                final List<String> guestsEmail) {
        this.id = id;
        this.initialDateTime = initialDateTime;
        this.finalDateTime = finalDateTime;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventColor = eventColor;
        this.organizerEmail = organizerEmail;
        this.guestsEmail = guestsEmail;
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

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public void setOrganizerEmail(final String organizerEmail) {
        this.organizerEmail = organizerEmail;
    }

    public List<String> getGuestsEmail() {
        return guestsEmail;
    }

    public void setGuestsEmail(final List<String> guestsEmail) {
        this.guestsEmail = guestsEmail;
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
}
