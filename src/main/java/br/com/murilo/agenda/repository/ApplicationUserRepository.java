package br.com.murilo.agenda.repository;

import br.com.murilo.agenda.entity.ApplicationUser;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends UserRepository<ApplicationUser, String> {
}
