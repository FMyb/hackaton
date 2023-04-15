package com.example.hackaton.controllers;


import com.example.hackaton.dto.EventDto;
import com.example.hackaton.dto.Point;
import com.example.hackaton.dto.TimeStatistic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class StatisticsController {

	@GetMapping("/statistics")
	public List<TimeStatistic> getTimeStatistics(@PathVariable long tsBefore,
												 @PathVariable long tsAfter,
												 @RequestParam String type,
												 @RequestParam Integer limit) {

		return new ArrayList<>();
	}

	@GetMapping("/graph")
	public List<Point> getDependencyData(@PathVariable long id,
										 @RequestParam String type,
										 @PathVariable long tsBefore,
										 @PathVariable long tsAfter) {

		return new ArrayList<>();
	}


	@GetMapping("/graph")
	public List<EventDto> getStac(@PathVariable long id,
								  @RequestParam String type,
								  @PathVariable long tsBefore,
								  @PathVariable long tsAfter) {

		return new ArrayList<>();
	}


}
