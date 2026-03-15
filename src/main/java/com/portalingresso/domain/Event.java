package com.portalingresso.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "events")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Integer totalTickets;

    @Column(nullable = false)
    private Integer availableTickets;

    public Event(String name, String location, LocalDateTime date, Integer totalTickets) {

        if (totalTickets <= 0) {
            throw new IllegalArgumentException("Total de ingressos deve ser maior que zero.");
        }

        this.name = name;
        this.location = location;
        this.date = date;
        this.totalTickets = totalTickets;
        this.availableTickets = totalTickets;
    }

    public void reserveTickets(int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (quantity > this.availableTickets) {
            throw new IllegalStateException("Sem ingressos suficientes para esse evento.");
        }

        this.availableTickets -= quantity;
    }
}