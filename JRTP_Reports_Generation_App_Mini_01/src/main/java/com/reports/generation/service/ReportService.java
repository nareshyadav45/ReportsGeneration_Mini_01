package com.reports.generation.service;

import java.util.List;

import com.reports.generation.entity.CitizenPlan;
import com.reports.generation.request.SearchRequest;

public interface ReportService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<CitizenPlan> search(SearchRequest searchRequest);
	
	public boolean exportDataExcel();
	
	public boolean exportDataPdf();	
}
