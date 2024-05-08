package com.reports.generation.service;

import java.util.List;

import com.reports.generation.entity.CitizenPlan;
import com.reports.generation.request.SearchRequest;
import com.reports.generation.response.SearchResponse;

public interface ReportService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public SearchResponse search(SearchRequest searchRequest);
	
	public boolean exportDataExcel();
	
	public boolean exportDataPdf();	
}
