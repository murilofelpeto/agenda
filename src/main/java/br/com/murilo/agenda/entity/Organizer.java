package br.com.murilo.agenda.entity;

import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@TypeAlias("Organizer")
public class Organizer extends EventUser {

    public Organizer(final String id,
                     final String name,
                     final String username,
                     final EventResponseEnum eventResponseEnum) {
        super(id, name, username, eventResponseEnum);
    }
}
