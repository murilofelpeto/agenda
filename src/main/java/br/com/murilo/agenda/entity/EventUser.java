package br.com.murilo.agenda.entity;

import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@TypeAlias("EventUser")
public class EventUser extends ApplicationUser {

    private EventResponseEnum eventResponseEnum;

    public EventUser(final String id,
                     final String name,
                     final String username,
                     final EventResponseEnum eventResponseEnum) {
        super(id, name, username, null);
        this.eventResponseEnum = eventResponseEnum;
    }

    public EventResponseEnum getEventResponseEnum() {
        return eventResponseEnum;
    }

    public void setEventResponseEnum(final EventResponseEnum eventResponseEnum) {
        this.eventResponseEnum = eventResponseEnum;
    }
}
