package com.example.hackaton.repository;

import com.example.hackaton.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Yaroslav Ilin
 */
@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
}
