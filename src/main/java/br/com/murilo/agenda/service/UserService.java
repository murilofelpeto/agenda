package br.com.murilo.agenda.service;

import br.com.murilo.agenda.entity.Role;
import br.com.murilo.agenda.entity.User;
import br.com.murilo.agenda.repository.RoleRepository;
import br.com.murilo.agenda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        throw new UsernameNotFoundException("Username " + username + " not found!");
    }

    public User createUser(final User user) {
        Optional<Role> roleOptional = roleRepository.findByRoleName("USER");
        if(roleOptional.isPresent()){
            user.addRole(roleOptional.get());
            return userRepository.insert(user);
        }
        throw new RuntimeException("Role not found!");
        //TODO Criar exception para role
    }
}
