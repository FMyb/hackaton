package com.example.hackaton.service;

import com.example.hackaton.dto.EventDto;
import com.example.hackaton.dto.Point;
import com.example.hackaton.dto.TimeStatistic;

import java.util.List;

/**
 * @author Yaroslav Ilin
 */
public interface StatisticService {
    List<TimeStatistic> getTimeStatistics(long tsBefore, long tsAfter, String type, Integer limit);

    List<Point> getDependencyData(long id, String type, long tsBefore, long tsAfter);

    List<EventDto> getStac(long id, String type, long tsBefore, long tsAfter);
}
