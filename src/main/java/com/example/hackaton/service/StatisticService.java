package com.example.hackaton.service;

import com.example.hackaton.dto.EventDto;
import com.example.hackaton.dto.EventInput;
import com.example.hackaton.dto.Point;
import com.example.hackaton.dto.TimeStatistic;
import com.example.hackaton.model.ArchiveState;

import java.util.List;
import java.util.function.Function;

/**
 * @author Yaroslav Ilin
 */
public interface StatisticService {
    List<TimeStatistic> getTimeStatistics(long tsBefore, long tsAfter, Function<ArchiveState, Double> avg, Integer limit);

    List<Point> getDependencyData(long id, String type, long tsBefore, long tsAfter);

    List<EventDto> getStac(long id, String type, long tsBefore, long tsAfter);

    void saveStats(EventInput input);

    void archiveData(long tsBefore, long tsAfter);

}
