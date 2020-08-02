package br.com.murilo.agenda.dto.response;

import java.util.Objects;

public class UserResponse {

    private String id;
    private String name;
    private String email;
    private PictureResponse profilePicture;

    public UserResponse(final String id,
                        final String name,
                        final String email,
                        final PictureResponse profilePicture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public PictureResponse getProfilePicture() {
        return profilePicture;
    }
}
