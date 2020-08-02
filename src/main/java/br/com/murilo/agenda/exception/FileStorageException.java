package br.com.murilo.agenda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
public class FileStorageException extends RuntimeException{

    public FileStorageException(final String message) {
        super(message);
    }
}
