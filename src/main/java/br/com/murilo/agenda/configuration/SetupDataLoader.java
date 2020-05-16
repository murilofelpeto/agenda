package br.com.murilo.agenda.configuration;

import br.com.murilo.agenda.entity.Role;
import br.com.murilo.agenda.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Optional;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    public SetupDataLoader(@Autowired final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {

        createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("USER");
    }

    private void createRoleIfNotFound(final String roleName) {
        Optional<Role> roleOptional = roleRepository.findByRoleName(roleName);
        if(!roleOptional.isPresent()){
            roleRepository.insert(new Role(roleName));
        }
    }
}
