package br.com.murilo.agenda.exception;

import java.util.List;

public class ValidationErrorResponse {

    private final List<ViolationErrorResponse> violantions;

    public ValidationErrorResponse(final List<ViolationErrorResponse> violantions) {
        this.violantions = violantions;
    }

    public List<ViolationErrorResponse> getViolantions() {
        return violantions;
    }
}
