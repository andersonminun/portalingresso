package com.portalingresso.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.portalingresso.domain.Event;

public record EventResponseDTO(
        UUID id,
        String name,
        String location,
        LocalDateTime date,
        Integer availableTickets
) {
    
    public static EventResponseDTO fromEntity(Event event) {
        return new EventResponseDTO(
                event.getId(),
                event.getName(),
                event.getLocation(),
                event.getDate(),
                event.getAvailableTickets()
        );
    }
}
