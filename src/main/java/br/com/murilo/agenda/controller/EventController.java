package br.com.murilo.agenda.controller;

import br.com.murilo.agenda.dto.request.EventRequest;
import br.com.murilo.agenda.dto.response.EventResponse;
import br.com.murilo.agenda.facade.EventFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private EventFacade eventFacade;

    public EventController(@Autowired EventFacade eventFacade) {
        this.eventFacade = eventFacade;
    }

    @GetMapping
    public List<EventResponse> findEventsBetweenDates(){
        return Collections.emptyList();
    }

    @PostMapping
    public EventResponse createEvent(@RequestBody EventRequest request) {
        //TODO retrieve organizer email by Authorization
        return eventFacade.createEvent(request);
    }
}
