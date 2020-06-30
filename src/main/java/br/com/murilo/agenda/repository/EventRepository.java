package br.com.murilo.agenda.repository;

import br.com.murilo.agenda.entity.Event;
import br.com.murilo.agenda.types.EventResponseEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    @Query("{organizer:{?0:{$ref:users,$id:{$oid:?1}}}}")
    List<Event> findByOrganizer(EventResponseEnum responseEnum, String organizerID);
}
