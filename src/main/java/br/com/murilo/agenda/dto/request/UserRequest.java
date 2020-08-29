package br.com.murilo.agenda.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserRequest {

    private String id;

    @NotNull(message = "{user.name.not.null}")
    private String name;

    @NotNull(message = "{user.email.not.null}")
    @Email(message = "{user.email.not.valid}")
    private String email;

    @NotNull(message = "{user.password.not.null}")
    private String password;

    public UserRequest(final String id,
                       final String name,
                       final String email,
                       final String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
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
}
