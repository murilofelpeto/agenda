package br.com.murilo.agenda.security;

import br.com.murilo.agenda.entity.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static br.com.murilo.agenda.types.SecurityEnums.EXPIRATION_TIME;
import static br.com.murilo.agenda.types.SecurityEnums.HEADER;
import static br.com.murilo.agenda.types.SecurityEnums.SECRET;
import static br.com.murilo.agenda.types.SecurityEnums.TOKEN_PREFIX;


import com.auth0.jwt.JWT;

/**
 * This class is responsible for generating and authenticate a @{@link ApplicationUser}
 */

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authManager;
    public JWTAuthenticationFilter(@Autowired AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
                                                final HttpServletResponse response) throws AuthenticationException {

        try {
            ApplicationUser user = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);

            return authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            Collections.emptyList()));
        } catch (IOException e) {
            throw new RuntimeException("Erro de deserialização do HTTP request");
        }
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request,
                                            final HttpServletResponse response,
                                            final FilterChain chain,
                                            final Authentication authResult) {

        Date now = new Date();
        Date expiration = new Date(now.getTime() + Integer.parseInt(EXPIRATION_TIME.getSecurityValue()));

        String token = JWT.create()
                .withSubject(((User)authResult.getPrincipal()).getUsername())
                .withExpiresAt(expiration)
                .sign(HMAC512(SECRET.getSecurityValue().getBytes()));

        response.addHeader(HEADER.getSecurityValue(),
                TOKEN_PREFIX.getSecurityValue() + token);
    }
}
