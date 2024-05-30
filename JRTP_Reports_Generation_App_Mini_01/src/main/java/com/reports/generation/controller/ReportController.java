package com.reports.generation.controller;

import java.io.IOException;
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
import jakarta.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;

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
		
		SearchResponse response = this.reportService.search(request);

		if(response.getReports().isEmpty()) {
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
			
		}else if(response.getReports().isEmpty()!=true) {
		
		return new ResponseEntity<>(response,HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
		}
	}
	
	
	    
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception{
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition","attachment;filename=plans.xls" );
		 try {
		        this.reportService.exportDataExcel(response);
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to generate Excel file");
		    }		
	}
	
	@GetMapping("/pdf")
    public void generatePdf(HttpServletResponse response) throws Exception{
    	
    	response.setContentType("application/pdf");
    	response.addHeader("Content-Disposition", "attachment;filename=plans.pdf");
    	this.reportService.exportDataPdf(response);
    	
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
