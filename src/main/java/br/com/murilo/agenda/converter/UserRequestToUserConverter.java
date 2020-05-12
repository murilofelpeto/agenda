package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.request.UserRequest;
import br.com.murilo.agenda.entity.Role;
import br.com.murilo.agenda.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRequestToUserConverter implements Converter<UserRequest, User> {

    @Autowired
    private ConversionService conversionService;

    @Override
    public User convert(final UserRequest userRequest) {
        final Set<Role> roles = userRequest.getRoles()
                .stream()
                .map(role -> conversionService.convert(role, Role.class))
                .collect(Collectors.toSet());

        String password = new BCryptPasswordEncoder().encode(userRequest.getPassword());

        return new User(userRequest.getId(),
                userRequest.getName(),
                userRequest.getEmail(),
                password,
                roles);
    }
}
