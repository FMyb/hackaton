package com.example.hackaton.repository;

import com.example.hackaton.model.ArchiveState;
import com.example.hackaton.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Yaroslav Ilin
 */
@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

	@Query("from event where timestamp = (select timestamp from event where timestamp >= :tsBefore and  timestamp <= :tsAfter)")
	List<Event> findAllBetween(@Param("tsBefore") long tsBefore,@Param("tsAfter") long tsAfter);

}
