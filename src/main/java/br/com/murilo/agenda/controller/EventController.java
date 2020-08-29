package br.com.murilo.agenda.controller;

import br.com.murilo.agenda.dto.request.EventCreationRequest;
import br.com.murilo.agenda.dto.request.EventRequest;
import br.com.murilo.agenda.dto.request.UpdateResponse;
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

    @GetMapping("/{id}")
    public EventResponse getEventById(@PathVariable("id") final String id) {
        return this.eventFacade.findEventById(id);
    }

    @PutMapping("/{id}")
    public EventResponse updateEvent(@PathVariable("id") String id,
                                     @RequestBody EventRequest eventRequest) {
        final String username = getUsername();
        return this.eventFacade.updateEvent(id, eventRequest, username);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable("id") String id,
                            @RequestBody EventRequest eventRequest) {
        final String username = getUsername();
        this.eventFacade.deleteEvent(id, eventRequest, username);
    }

    @PatchMapping("/{id}/response")
    public EventResponse updateResponse(@PathVariable("id") String id,
                                        @RequestBody UpdateResponse updateResponse) {
        final String username = getUsername();
        return eventFacade.updateEventResponse(username, updateResponse, id);
    }

    private String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
