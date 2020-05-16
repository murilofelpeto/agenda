package br.com.murilo.agenda.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static br.com.murilo.agenda.types.SecurityEnums.*;

/**
 * This class is responsible for the authorization of the @{@link br.com.murilo.agenda.entity.ApplicationUser}
 */

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(final AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(HEADER.getSecurityValue());
        if (header == null || !header.startsWith(TOKEN_PREFIX.getSecurityValue())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(final HttpServletRequest request) {
        String token = request.getHeader(HEADER.getSecurityValue());
        if(token != null) {
            String user = JWT.require(
                    Algorithm.HMAC512(
                            SECRET.getSecurityValue().getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX.getSecurityValue(), ""))
                    .getSubject();

            if(user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
            return null;
        }
        return null;
    }
}
