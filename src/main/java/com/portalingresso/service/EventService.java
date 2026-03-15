package com.portalingresso.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portalingresso.domain.Event;
import com.portalingresso.dto.EventRequestDTO;
import com.portalingresso.dto.EventResponseDTO;
import com.portalingresso.repository.EventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public EventResponseDTO createEvent(EventRequestDTO requestDTO) {
        
        Event newEvent = new Event(
                requestDTO.name(),
                requestDTO.location(),
                requestDTO.date(),
                requestDTO.totalTickets()
        );

        Event savedEvent = eventRepository.save(newEvent);

        return EventResponseDTO.fromEntity(savedEvent);
    }

    @Transactional(readOnly = true)
    public List<EventResponseDTO> getAllEvents() {
        
        return eventRepository.findAll()
                .stream()
                .map(EventResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public void processTicketReservation(UUID eventId, Integer quantity) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado para o ID: " + eventId));

        if (event.getAvailableTickets() < quantity) {
            log.error("ESTOQUE INSUFICIENTE para o evento: {}. Pedido: {}, Disponível: {}", 
                      event.getName(), quantity, event.getAvailableTickets());
            return;
        }

        event.setAvailableTickets(event.getAvailableTickets() - quantity);
        eventRepository.save(event);

        log.info("ESTOQUE ATUALIZADO: Evento '{}' agora possui {} ingressos.", 
                 event.getName(), event.getAvailableTickets());
    }
}