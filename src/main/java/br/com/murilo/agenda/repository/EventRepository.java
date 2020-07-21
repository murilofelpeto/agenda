package br.com.murilo.agenda.repository;

import br.com.murilo.agenda.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    /*
    {"organizer.user" : {"$ref":"users","$id":{"$oid":"5ef2a158fb581209128cbcce"}}}
     */
    List<Event> findByInitialDateTimeBetweenAndOrganizerUserId(final LocalDateTime initialDate, final LocalDateTime endDate, final String id);

}
