package br.com.murilo.agenda.entity;

import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Guest extends EventUser{

    public Guest(final String id,
                 final String name,
                 final String email,
                 final EventResponseEnum eventResponseEnum) {
        super(id, name, email, eventResponseEnum);
    }


}
