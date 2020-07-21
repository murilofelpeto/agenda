package br.com.murilo.agenda.controller;

import br.com.murilo.agenda.dto.request.EventCreationRequest;
import br.com.murilo.agenda.dto.response.EventResponse;
import br.com.murilo.agenda.facade.EventFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private EventFacade eventFacade;

    public EventController(@Autowired EventFacade eventFacade) {
        this.eventFacade = eventFacade;
    }

    /*@GetMapping
    public List<EventResponse> findEventsBetweenDates(@RequestParam("initialDate") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")LocalDateTime initialDate,
                                                      @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")LocalDateTime endDate){
        String username = getUsername();
        return eventFacade.findEventsBetweenDates(initialDate, endDate, username);
    }*/

    @PostMapping
    public EventResponse createEvent(@RequestBody EventCreationRequest request) {
        request.setOrganizerEmail(getUsername());
        return eventFacade.createEvent(request);
    }

    @GetMapping
    public List<EventResponse> findEvents(@RequestParam("initialDate") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")LocalDateTime initialDate,
                                          @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")LocalDateTime endDate) {
        final String username = getUsername();
        return this.eventFacade.findEvents(username, initialDate, endDate);
    }

    private String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
