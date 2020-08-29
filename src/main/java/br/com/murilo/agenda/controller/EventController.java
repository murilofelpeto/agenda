package br.com.murilo.agenda.controller;

import br.com.murilo.agenda.dto.request.EventCreationRequest;
import br.com.murilo.agenda.dto.request.EventRequest;
import br.com.murilo.agenda.dto.request.UpdateResponse;
import br.com.murilo.agenda.dto.response.EventResponse;
import br.com.murilo.agenda.facade.EventFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Api("Manage events")
@RestController
@RequestMapping("/event")
public class EventController {

    //TODO Create unit tests
    //TODO Send to heroku using travis-CI

    private final EventFacade eventFacade;

    public EventController(@Autowired EventFacade eventFacade) {
        this.eventFacade = eventFacade;
    }

    @ApiOperation("Create a new event")
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventCreationRequest request) {
        request.setOrganizerEmail(getUsername());
        return new ResponseEntity<>(eventFacade.createEvent(request), HttpStatus.CREATED);
    }

    @ApiOperation("Find events filtering by date")
    @GetMapping
    public List<EventResponse> findEvents(@RequestParam("initialDate") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")LocalDateTime initialDate,
                                          @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")LocalDateTime endDate) {
        final String username = getUsername();
        return this.eventFacade.findEvents(username, initialDate, endDate);
    }

    @ApiOperation("Find event by id")
    @GetMapping("/{id}")
    public EventResponse getEventById(@PathVariable("id") final String id) {
        return this.eventFacade.findEventById(id);
    }

    @ApiOperation("Update event. Only organizer can update")
    @PutMapping("/{id}")
    public EventResponse updateEvent(@PathVariable("id") String id,
                                     @Valid @RequestBody EventRequest eventRequest) {
        final String username = getUsername();
        return this.eventFacade.updateEvent(id, eventRequest, username);
    }

    @ApiOperation("Delete event. Only organizer can delete.")
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable("id") String id,
                            @Valid @RequestBody EventRequest eventRequest) {
        final String username = getUsername();
        this.eventFacade.deleteEvent(id, eventRequest, username);
    }

    @ApiOperation("Update an event response.")
    @PatchMapping("/{id}/response")
    public EventResponse updateResponse(@PathVariable("id") String id,
                                        @Valid @RequestBody UpdateResponse updateResponse) {
        final String username = getUsername();
        return eventFacade.updateEventResponse(username, updateResponse, id);
    }

    private String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
