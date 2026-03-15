package com.portalingresso.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.portalingresso.dto.TicketOrderDTO;
import com.portalingresso.service.EventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketOrderConsumer {

    private final EventService eventService;

    @KafkaListener(topics = "ticket-orders-topic", groupId = "catalog-group")
    public void consume(TicketOrderDTO order) {
        
        log.info("Processando reserva para o evento: {}", order.eventId());
        
        try {
            eventService.processTicketReservation(order.eventId(), order.quantity());
        } catch (Exception e) {
            log.error("Erro ao processar reserva: {}", e.getMessage());
        }
    }
}
