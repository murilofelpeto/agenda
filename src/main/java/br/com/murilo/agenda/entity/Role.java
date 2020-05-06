package br.com.murilo.agenda.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Document
public class Role implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 7512843946272919989L;

    @Id
    private String id;
    private String roleName;

    @Override
    public String getAuthority() {
        return this.roleName;
    }

    public Role(final String id, final String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role(final String roleName) {
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
}
