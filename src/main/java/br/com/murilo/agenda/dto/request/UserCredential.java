package br.com.murilo.agenda.dto.request;

import java.util.Objects;

public class UserCredential {

    private String email;
    private String password;

    public UserCredential(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCredential)) return false;
        final UserCredential that = (UserCredential) o;
        return getEmail().equals(that.getEmail()) &&
                getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }
}
