package br.com.murilo.agenda.dto.response;

import java.util.Objects;

public class TokenDTO {

    private String username;
    private String token;

    public TokenDTO(final String username, final String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenDTO)) return false;
        final TokenDTO tokenDTO = (TokenDTO) o;
        return Objects.equals(getUsername(), tokenDTO.getUsername()) &&
                Objects.equals(getToken(), tokenDTO.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getToken());
    }
}
