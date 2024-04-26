package com.reports.generation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.reports.generation.service.ReportService;

import jakarta.persistence.Index;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}



	//
}
