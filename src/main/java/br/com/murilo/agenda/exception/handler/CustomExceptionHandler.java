package br.com.murilo.agenda.exception.handler;

import br.com.murilo.agenda.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotExistsException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceNotExists(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceAlreadyExists(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FileStorageException.class)
    public final ResponseEntity<ExceptionResponse> handleFileStorageException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INSUFFICIENT_STORAGE);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleFileNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventNotModifiedException.class)
    public final ResponseEntity<ExceptionResponse> handleEventNotModifiedException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
