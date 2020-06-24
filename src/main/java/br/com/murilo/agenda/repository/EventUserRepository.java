package br.com.murilo.agenda.repository;

import br.com.murilo.agenda.entity.EventUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventUserRepository extends MongoRepository<EventUser, String> {
}
