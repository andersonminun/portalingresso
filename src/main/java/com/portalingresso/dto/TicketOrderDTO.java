package com.portalingresso.dto;

import java.util.UUID;

public record TicketOrderDTO(
        UUID eventId,
        Integer quantity,
        String userEmail
) {}