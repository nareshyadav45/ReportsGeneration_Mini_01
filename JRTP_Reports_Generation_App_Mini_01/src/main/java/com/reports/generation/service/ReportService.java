package com.reports.generation.service;

import java.util.List;

import org.springframework.http.HttpHeaders;

import com.reports.generation.entity.CitizenPlan;
import com.reports.generation.request.SearchRequest;
import com.reports.generation.response.SearchResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public SearchResponse search(SearchRequest searchRequest);
	
	public boolean exportDataExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportDataPdf(HttpServletResponse response) throws Exception;	
}
