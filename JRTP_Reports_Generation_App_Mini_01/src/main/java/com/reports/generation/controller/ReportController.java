package com.reports.generation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.reports.generation.entity.CitizenPlan;
import com.reports.generation.request.SearchRequest;
import com.reports.generation.response.SearchResponse;
import com.reports.generation.service.ReportService;

import jakarta.persistence.Index;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/")
	public String indexPage(Model model) {
		// SearchRequest request=new SearchRequest();
		// model.addAttribute("search", new SearchRequest());
		init(model);
//		model.addAttribute("plan_names", reportService.getPlanNames());
//		model.addAttribute("plan_status",reportService.getPlanStatuses());
		return "index";
	}

	@PostMapping("/search")
	public String Search(SearchRequest request, Model model) {
		System.out.println(request);

		//List<CitizenPlan> plans = this.reportService.search(request);

		//model.addAttribute("plans", plans);

		init(model);
		return "index";
	}
	
	@PostMapping("/searchAll")
	public ResponseEntity<SearchResponse> SearchAll(@RequestBody SearchRequest request) {
		System.out.println(request);

		//List<CitizenPlan> plans = this.reportService.search(request);
		SearchResponse response = this.reportService.search(request);

		//model.addAttribute("plans", plans);

		//init(model);
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	

	public void init(Model model) {
		model.addAttribute("search", new SearchRequest());
		model.addAttribute("plan_names", reportService.getPlanNames());
		model.addAttribute("plan_status", reportService.getPlanStatuses());
	}

//	@GetMapping("/planNames")
//	public ResponseEntity<List<String>> fetchPlanNames(){
//		List<String> planNames = this.reportService.getPlanNames();
//		return new ResponseEntity<List<String>>(planNames,HttpStatusCode.valueOf(200));
//	}

}
