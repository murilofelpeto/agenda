package br.com.murilo.agenda.dto.request;

import java.util.Objects;

public class RoleRequest {

    private String id;
    private String roleName;

    public RoleRequest(final String id, final String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public RoleRequest(final String roleName) {
        this.roleName = roleName;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleRequest)) return false;
        final RoleRequest that = (RoleRequest) o;
        return getId().equals(that.getId()) &&
                getRoleName().equals(that.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoleName());
    }
}
