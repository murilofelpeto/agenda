package br.com.murilo.agenda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class EventNotModifiedException extends RuntimeException {

    public EventNotModifiedException(final String message) {
        super(message);
    }
}
