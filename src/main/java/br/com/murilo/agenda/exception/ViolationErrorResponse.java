package br.com.murilo.agenda.exception;

public class ViolationErrorResponse {

    private final String fieldName;
    private final String message;

    public ViolationErrorResponse(final String fieldName, final String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
