package br.com.murilo.agenda.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Document
public class ApplicationUser {

    private static final long serialVersionUID = -9153507237957973880L;

    @Id
    private String id;
    private String username;
    private String password;

    public ApplicationUser(){}

    public ApplicationUser(final String id, final String username, final String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public ApplicationUser(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
