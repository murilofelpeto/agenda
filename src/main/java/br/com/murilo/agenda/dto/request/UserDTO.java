package br.com.murilo.agenda.dto.request;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDTO {

    private String id;
    private String name;
    private String email;
    private String password;
    private Set<RoleRequest> roles;

    public UserDTO(final String id, final String name, final String email, final String password, final Set<RoleRequest> roles) {
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
        if (!(o instanceof UserDTO)) return false;
        final UserDTO userDTO = (UserDTO) o;
        return getId().equals(userDTO.getId()) &&
                getEmail().equals(userDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }
}
