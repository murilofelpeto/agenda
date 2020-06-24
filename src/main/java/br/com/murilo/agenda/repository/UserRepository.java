package br.com.murilo.agenda.repository;

import br.com.murilo.agenda.entity.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<T, ID extends String> extends MongoRepository<T, ID> {

    Optional<T> findByUsername(String username);
    List<T> findByUsernameIn(Collection<String> usernames);
}
