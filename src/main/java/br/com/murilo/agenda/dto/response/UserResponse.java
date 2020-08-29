package br.com.murilo.agenda.dto.response;

public class UserResponse {

    private final String id;
    private final String name;
    private final String email;
    private final PictureResponse profilePicture;

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
