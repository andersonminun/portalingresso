package com.portalingresso.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portalingresso.domain.Event;

public interface EventRepository extends JpaRepository<Event, UUID> {
    
}
