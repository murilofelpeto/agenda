package br.com.murilo.agenda.dto.response;

import java.util.Objects;

public class UserResponse {

    private String id;
    private String email;

    public UserResponse(final String id, final String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResponse)) return false;
        final UserResponse that = (UserResponse) o;
        return getId().equals(that.getId()) &&
                getEmail().equals(that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }
}
