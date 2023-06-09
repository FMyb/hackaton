package com.example.hackaton.repository;

import com.example.hackaton.model.ArchiveState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yaroslav Ilin
 */
@Repository
public interface ArchiveStateRepository extends JpaRepository<ArchiveState, Long> {
    @Query("from archive_state where timestamp = (select timestamp from archive_state where timestamp >= :timestamp order by timestamp limit 1)")
    List<ArchiveState> findAllByTimestamp(@Param("timestamp") long timestamp);

    List<ArchiveState> findAllByMethod_MethodIdAndTimestampGreaterThanEqualAndTimestampLessThanEqualOrderByTimestamp(long methodId, long tsBefore, long tsAfter);
}
