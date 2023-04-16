package com.example.hackaton.controllers;

import com.example.hackaton.dto.EventInput;
import com.example.hackaton.service.StatisticService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DataController {

	private final StatisticService statisticService;


	public DataController(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	@PostMapping("/addEvent")
	public void getTimeStatistics(@RequestBody EventInput input) {
		statisticService.saveStats(input);
	}
}
