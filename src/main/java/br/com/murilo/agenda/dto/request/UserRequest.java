package br.com.murilo.agenda.dto.request;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserRequest {

    private String id;
    private String name;
    private String email;
    private String password;
    private Set<RoleRequest> roles;

    public UserRequest(final String id, final String name, final String email, final String password, final Set<RoleRequest> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
        this.roles.addAll(roles);
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

    public Set<RoleRequest> getRoles() {
        return roles;
    }

    public void setRoles(final Set<RoleRequest> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRequest)) return false;
        final UserRequest userRequest = (UserRequest) o;
        return getId().equals(userRequest.getId()) &&
                getEmail().equals(userRequest.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }
}
