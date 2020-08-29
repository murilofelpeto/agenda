package br.com.murilo.agenda.entity;

import br.com.murilo.agenda.entity.ApplicationUser;
import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class EventUserResponse {

    @DBRef
    private ApplicationUser user;
    private EventResponseEnum response;

    public EventUserResponse(final ApplicationUser user, final EventResponseEnum response) {
        this.user = user;
        this.response = response;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public String getUsername() {
        return this.user.getUsername();
    }

    public void setUser(final ApplicationUser user) {
        this.user = user;
    }

    public EventResponseEnum getResponse() {
        return response;
    }

    public void setResponse(final EventResponseEnum response) {
        this.response = response;
    }
}
