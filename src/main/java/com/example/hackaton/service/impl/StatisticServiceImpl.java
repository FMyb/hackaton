package com.example.hackaton.service.impl;

import com.example.hackaton.dto.EventDto;
import com.example.hackaton.dto.Point;
import com.example.hackaton.dto.TimeStatistic;
import com.example.hackaton.model.ArchiveState;
import com.example.hackaton.repository.ArchiveStateRepository;
import com.example.hackaton.service.StatisticService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yaroslav Ilin
 */
@Service
public class StatisticServiceImpl implements StatisticService {
    private final ArchiveStateRepository archiveStateRepository;

    public StatisticServiceImpl(
            ArchiveStateRepository archiveStateRepository
    ) {
        this.archiveStateRepository = archiveStateRepository;
    }

    @Override
    public List<TimeStatistic> getTimeStatistics(long tsBefore, long tsAfter, String type, Integer limit) {
        List<ArchiveState> archiveStatesBefore = archiveStateRepository.findAllByTimestamp(tsBefore);
        List<ArchiveState> archiveStatesAfter = archiveStateRepository.findAllByTimestamp(tsAfter);
        return null;
    }

    @Override
    public List<Point> getDependencyData(long id, String type, long tsBefore, long tsAfter) {
        return null;
    }

    @Override
    public List<EventDto> getStac(long id, String type, long tsBefore, long tsAfter) {
        return null;
    }
}
