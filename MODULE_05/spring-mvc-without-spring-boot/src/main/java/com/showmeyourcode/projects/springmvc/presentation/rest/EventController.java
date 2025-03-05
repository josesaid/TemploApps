package com.showmeyourcode.projects.springmvc.presentation.rest;

import com.showmeyourcode.projects.springmvc.application.EventService;
import com.showmeyourcode.projects.springmvc.domain.model.EventDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = EventController.CONTROLLER_PATH)
public class EventController {

    public static final String CONTROLLER_PATH = "/api/v1/events";

    private final EventService eventService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventDto> getAll(
            @RequestParam(required = false, name = "withUsers", defaultValue = "false") boolean withUsers,
            @RequestParam(required = false, name = "available", defaultValue = "true") boolean onlyAvailable
    ) {
        if (onlyAvailable) {
            return eventService.getAllAvailable(withUsers);
        } else {
            return eventService.getAll(withUsers);
        }
    }
}
