package br.com.murilo.agenda.repository;

import br.com.murilo.agenda.entity.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<ApplicationUser, String> {

    Optional<ApplicationUser> findByUsername(String username);

    List<ApplicationUser> findByUsernameIn(Collection<String> usernames);
}
