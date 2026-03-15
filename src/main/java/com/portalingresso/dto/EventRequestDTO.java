package com.portalingresso.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventRequestDTO(
        @NotBlank(message = "O nome do evento é obrigatório")
        String name,

        @NotBlank(message = "A localização é obrigatória")
        String location,

        @NotNull(message = "A data é obrigatória")
        @FutureOrPresent(message = "A data do evento não pode estar no passado")
        LocalDateTime date,

        @NotNull(message = "A quantidade de ingressos é obrigatória")
        @Min(value = 1, message = "O evento deve ter pelo menos 1 ingresso")
        Integer totalTickets
) {}
