package com.example.hackaton.repository;

import com.example.hackaton.model.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yaroslav Ilin
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("FROM event e WHERE e.finishTimestamp >= :tsBefore AND e.finishTimestamp <= :tsAfter")
    List<Event> getStatesForArchive(@Param("tsBefore") long tsBefore, @Param("tsAfter") long tsAfter);

    @Query("from event e where e.finishTimestamp >= :tsBefore and e.finishTimestamp <= :tsAfter and e.method.methodId = :methodId order by e.startTimestamp")
    List<Event> getEventsByMethodId(@Param("methodId") long methodId, @Param("tsBefore") long tsBefore, @Param("tsAfter") long tsAfter, Pageable pageable);
}
