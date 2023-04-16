package com.example.hackaton.controllers;


import com.example.hackaton.dto.EventDto;
import com.example.hackaton.dto.Point;
import com.example.hackaton.dto.TimeStatistic;
import com.example.hackaton.model.ArchiveState;
import com.example.hackaton.service.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:3000")
public class StatisticsController {
    private final StatisticService statisticService;

    public StatisticsController(
            StatisticService statisticService
    ) {
        this.statisticService = statisticService;
    }

    @GetMapping("/statistics")
    // return list statistic
    public List<TimeStatistic> getTimeStatistics(
            @RequestParam long tsBefore,
            @RequestParam long tsAfter,
            @RequestParam String type,
            @RequestParam Integer limit
    ) {
        Function<ArchiveState, Double> typeMapper = switch (type) {
            case "BYTE_CNT" -> ArchiveState::getAvgByteCount;
            case "EXEC_TIME" -> ArchiveState::getAvgTime;
            default -> null;
        };
        if (typeMapper == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad type");
        }
        if (limit == null) {
            limit = 5;
        }
        return statisticService.getTimeStatistics(tsBefore, tsAfter, typeMapper, limit);
    }

    @GetMapping("/graph/{id}")
    public List<Point> getDependencyData(
            @PathVariable("id") long id,
            @RequestParam String type,
            @RequestParam long tsBefore,
            @RequestParam long tsAfter
    ) {
        Function<ArchiveState, Double> typeMapper = switch (type) {
            case "BYTE_CNT" -> ArchiveState::getAvgByteCount;
            case "EXEC_TIME" -> ArchiveState::getAvgTime;
            default -> null;
        };
        if (typeMapper == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad type");
        }
        return statisticService.getDependencyData(id, typeMapper, tsBefore, tsAfter);
    }


    @GetMapping("/stac/{id}")
    public List<EventDto> getStac(
            @PathVariable("id") long id,
            @RequestParam String type,
            @RequestParam long tsBefore,
            @RequestParam long tsAfter,
            @RequestParam int limit
            ) {
        Function<ArchiveState, Double> typeMapper = switch (type) {
            case "BYTE_CNT" -> ArchiveState::getAvgByteCount;
            case "EXEC_TIME" -> ArchiveState::getAvgTime;
            default -> null;
        };
        if (typeMapper == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad type");
        }
        return statisticService.getStac(id, typeMapper, tsBefore, tsAfter, limit);
    }


}
