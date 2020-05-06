package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.request.UserDTO;
import br.com.murilo.agenda.entity.Role;
import br.com.murilo.agenda.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDTOToUserConverter implements Converter<UserDTO, User> {

    @Autowired
    private ConversionService conversionService;

    @Override
    public User convert(final UserDTO userDTO) {
        final Set<Role> roles = userDTO.getRoles()
                .stream()
                .map(role -> conversionService.convert(role, Role.class))
                .collect(Collectors.toSet());

        return new User(userDTO.getId(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                roles);
    }
}
