package br.com.murilo.agenda.dto.response;

import br.com.murilo.agenda.entity.ApplicationUser;
import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.types.EventResponseEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;

public class Response {

    private String response;
    private String email;

    public Response() {
    }

    public Response(final EventResponseEnum response, final String email) {
        this.response = response.getResponse();
        this.email = email;
    }


    public String getResponse() {
        return response;
    }

    public void setResponse(final EventResponseEnum response) {
        this.response = response.getResponse();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

}
