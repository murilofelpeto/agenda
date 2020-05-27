package br.com.murilo.agenda.repository;

import br.com.murilo.agenda.entity.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    @Query("select e from Event where e.initialDateTime >= :startDate and e.finalDateTime <= :endDate and e.organizer.id = :id")
    List<Event> findEventsBetweenStartDateAndEndDate(@Param("startDate") LocalDateTime startDate,
                                                     @Param("endDate")LocalDateTime endDate,
                                                     @Param("id")String id);

    List<Event> findByInitialDateTimeBetweenAndOrganizerId(LocalDateTime initialDateTime, LocalDateTime endDateTime, String userID);
}
