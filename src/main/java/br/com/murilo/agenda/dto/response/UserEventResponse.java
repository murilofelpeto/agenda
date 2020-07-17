package br.com.murilo.agenda.dto.response;

import br.com.murilo.agenda.types.EventResponseEnum;

public class UserEventResponse {

    private String username;
    private EventResponseEnum response;

    public UserEventResponse(final String username, final EventResponseEnum response) {
        this.username = username;
        this.response = response;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public EventResponseEnum getResponse() {
        return response;
    }

    public void setResponse(final EventResponseEnum response) {
        this.response = response;
    }
}
