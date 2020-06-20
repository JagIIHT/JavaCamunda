package com.example.camunda.controller;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@Autowired
	private RuntimeService runtimeService;

	@GetMapping("start")
	public void startCamunda() {
		Map<String, Object> variables = new HashMap<>();
		variables.put("AppStatus", "App Started");
		this.runtimeService.startProcessInstanceByKey("Process_0mhuk6t", variables);
	}
}
