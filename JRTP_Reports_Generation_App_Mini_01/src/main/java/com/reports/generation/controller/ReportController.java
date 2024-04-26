package com.reports.generation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@GetMapping("/planNames")
	public ResponseEntity<List<String>> fetchPlanNames(){
		List<String> planNames = this.reportService.getPlanNames();
		return new ResponseEntity<List<String>>(planNames,HttpStatusCode.valueOf(200));
	}
	
}
