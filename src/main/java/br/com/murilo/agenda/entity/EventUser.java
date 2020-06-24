package br.com.murilo.agenda.entity;

import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "eventUser")
public class EventUser {

    @Id
    private String id;
    private EventResponseEnum response;

    @DBRef
    private ApplicationUser user;

    public EventUser(final String id, final EventResponseEnum response, final ApplicationUser user) {
        this.id = id;
        this.response = response;
        this.user = user;
    }

    public EventUser(final EventResponseEnum response, final ApplicationUser user) {
        this.response = response;
        this.user = user;
    }

    public EventUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public EventResponseEnum getResponse() {
        return response;
    }

    public void setResponse(final EventResponseEnum response) {
        this.response = response;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(final ApplicationUser user) {
        this.user = user;
    }

    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof EventUser)) return false;
        final EventUser eventUser = (EventUser) o;
        return Objects.equals(getId(), eventUser.getId()) &&
                Objects.equals(getUser(), eventUser.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser());
    }
}
