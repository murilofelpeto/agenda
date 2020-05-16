package br.com.murilo.agenda.service;

import br.com.murilo.agenda.entity.ApplicationUser;
import br.com.murilo.agenda.repository.RoleRepository;
import br.com.murilo.agenda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()){
            ApplicationUser user = userOptional.get();
            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        }
        throw new UsernameNotFoundException("Username " + username + " not found!");
    }

    public ApplicationUser createUser(final ApplicationUser user) {
            return userRepository.insert(user);
    }
}
