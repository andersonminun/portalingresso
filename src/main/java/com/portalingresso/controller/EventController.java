package com.portalingresso.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portalingresso.dto.EventRequestDTO;
import com.portalingresso.dto.EventResponseDTO;
import com.portalingresso.service.EventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@Valid @RequestBody EventRequestDTO request) {
        
        EventResponseDTO createdEvent = eventService.createEvent(request);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {

        List<EventResponseDTO> events = eventService.getAllEvents();
        
        return ResponseEntity.ok(events);
    }

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        
        return ResponseEntity.ok("Teste");
    }
}