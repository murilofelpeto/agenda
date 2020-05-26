package br.com.murilo.agenda.entity;

import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EventUser extends ApplicationUser {

    private EventResponseEnum eventResponseEnum;

    public EventUser(final String id,
                     final String name,
                     final String email,
                     final EventResponseEnum eventResponseEnum) {
        this.eventResponseEnum = eventResponseEnum;
        super.setId(id);
        super.setName(name);
        super.setUsername(email);
    }

    public EventResponseEnum getEventResponseEnum() {
        return eventResponseEnum;
    }

    public void setEventResponseEnum(final EventResponseEnum eventResponseEnum) {
        this.eventResponseEnum = eventResponseEnum;
    }
}
