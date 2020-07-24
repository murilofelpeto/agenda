package br.com.murilo.agenda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotExistsException extends RuntimeException{

    public ResourceNotExistsException(final String message) {
        super(message);
    }
}
