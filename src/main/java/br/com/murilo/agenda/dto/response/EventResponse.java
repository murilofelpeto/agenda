package br.com.murilo.agenda.dto.response;

import br.com.murilo.agenda.types.EventResponseEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventResponse {

    private String id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime initialDateTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime finalDateTime;
    private String eventColor;
    private Map<EventResponseEnum, String> organizerResponse;
    private Map<EventResponseEnum, List<String>> guestResponse;

    public EventResponse(){
        this.organizerResponse = new HashMap<>();
        this.guestResponse = new HashMap<>();
    }

    public EventResponse(final String id,
                         final LocalDateTime initialDateTime,
                         final LocalDateTime finalDateTime,
                         final String eventColor,
                         final Map<EventResponseEnum, String> organizerResponse,
                         final Map<EventResponseEnum, List<String>> guestResponse) {
        this.id = id;
        this.initialDateTime = initialDateTime;
        this.finalDateTime = finalDateTime;
        this.eventColor = eventColor;
        this.organizerResponse = new HashMap<>();
        this.organizerResponse.putAll(organizerResponse);
        this.guestResponse = new HashMap<>();
        this.guestResponse.putAll(guestResponse);
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

    public Map<EventResponseEnum, String> getOrganizerResponse() {
        return organizerResponse;
    }

    public Map<EventResponseEnum, List<String>> getGuestResponse() {
        return guestResponse;
    }
}
