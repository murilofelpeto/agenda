package br.com.murilo.agenda.dto.request;

import br.com.murilo.agenda.types.EventResponseEnum;

public class UpdateResponse {

    private EventResponseEnum response;

    public UpdateResponse(final EventResponseEnum response) {
        this.response = response;
    }

    public EventResponseEnum getResponse() {
        return response;
    }
}
