package br.com.murilo.agenda.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

    private List<ViolationErrorResponse> violantions = new ArrayList<>();

    public ValidationErrorResponse(final List<ViolationErrorResponse> violantions) {
        this.violantions = violantions;
    }

    public List<ViolationErrorResponse> getViolantions() {
        return violantions;
    }
}
