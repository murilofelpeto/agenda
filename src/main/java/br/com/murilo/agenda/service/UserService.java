package br.com.murilo.agenda.service;

import br.com.murilo.agenda.entity.ApplicationUser;
import br.com.murilo.agenda.exception.ResourceNotExistsException;
import br.com.murilo.agenda.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND = "User not found!";
    private final ApplicationUserRepository applicationUserRepository;

    public UserService(@Autowired ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> userOptional = applicationUserRepository.findByUsername(username);
        if(userOptional.isPresent()){
            ApplicationUser user = userOptional.get();
            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        }
        throw new UsernameNotFoundException("Username " + username + " not found!");
    }

    public ApplicationUser findByUsername(final String username) {
        Optional<ApplicationUser> userOptional = applicationUserRepository.findByUsername(username);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        throw new UsernameNotFoundException("Username " + username + " not found!");
    }

    public List<ApplicationUser> findUsersByUsername(Collection<String> usernames){
        return applicationUserRepository.findByUsernameIn(usernames);
    }

    public ApplicationUser findById(final String id) {
        final Optional<ApplicationUser> optionalUser = this.applicationUserRepository.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new ResourceNotExistsException(USER_NOT_FOUND);
    }

    public ApplicationUser createUser(final ApplicationUser user) {
            return applicationUserRepository.insert(user);
    }

    public ApplicationUser update(final String id, final ApplicationUser user) {
        if(userExists(id)) {
            user.setId(id);
            return this.applicationUserRepository.save(user);
        }
        throw new ResourceNotExistsException(USER_NOT_FOUND);
    }

    private Boolean userExists(final String id) {
        return this.applicationUserRepository.findById(id).isPresent();
    }
}
