package br.com.murilo.agenda.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "users")
public class ApplicationUser {

    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private Picture profilePicture;

    public ApplicationUser(){}

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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(final Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationUser)) return false;
        final ApplicationUser user = (ApplicationUser) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
