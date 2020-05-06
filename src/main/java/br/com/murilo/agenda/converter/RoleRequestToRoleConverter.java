package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.request.RoleRequest;
import br.com.murilo.agenda.entity.Role;
import org.springframework.core.convert.converter.Converter;

public class RoleRequestToRoleConverter implements Converter<RoleRequest, Role> {

    @Override
    public Role convert(final RoleRequest roleRequest) {
        final Role role = new Role(roleRequest.getId(), roleRequest.getRoleName());
        return role;
    }
}
