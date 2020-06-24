package br.com.murilo.agenda.service;

import br.com.murilo.agenda.entity.EventUser;
import br.com.murilo.agenda.repository.EventUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventUserService {

    private EventUserRepository eventUserRepository;

    public EventUserService(@Autowired EventUserRepository eventUserRepository) {
        this.eventUserRepository = eventUserRepository;
    }

    public EventUser save(final EventUser organizer) {
        return eventUserRepository.save(organizer);
    }

    public Set<EventUser> saveAll(final Set<EventUser> guests) {
        return eventUserRepository.saveAll(guests).stream().collect(Collectors.toSet());
    }
}
