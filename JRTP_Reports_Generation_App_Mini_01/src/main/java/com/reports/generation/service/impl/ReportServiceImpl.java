package com.reports.generation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reports.generation.entity.CitizenPlan;
import com.reports.generation.repo.CitizenPlanRepository;
import com.reports.generation.request.SearchRequest;
import com.reports.generation.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private CitizenPlanRepository citizenPlanRepository;

	@Override
	public List<String> getPlanNames() {
		
		List<String> fetchPlanNames =citizenPlanRepository.fetchPlanNames();
		
		return fetchPlanNames;
	}

	@Override
	public List<String> getPlanStatuses() {
		
		return null;
	}

	@Override
	public List<CitizenPlan> search(SearchRequest searchRequest) {
		
		return null;
	}

	@Override
	public boolean exportDataExcel() {
		
		return false;
	}

	@Override
	public boolean exportDataPdf() {
		
		return false;
	}

}
