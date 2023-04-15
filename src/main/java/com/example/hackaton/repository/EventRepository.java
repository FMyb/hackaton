package com.example.hackaton.repository;

import com.example.hackaton.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Yaroslav Ilin
 */
public interface EventRepository extends JpaRepository<Event, UUID> {
}
