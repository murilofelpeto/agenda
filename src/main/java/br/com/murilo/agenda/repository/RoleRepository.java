package br.com.murilo.agenda.repository;

import br.com.murilo.agenda.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByRoleName(String roleName);
}
