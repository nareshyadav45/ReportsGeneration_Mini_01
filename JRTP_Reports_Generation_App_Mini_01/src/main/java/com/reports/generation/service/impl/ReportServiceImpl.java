package com.reports.generation.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.reports.generation.entity.CitizenPlan;
import com.reports.generation.repo.CitizenPlanRepository;
import com.reports.generation.request.SearchRequest;
import com.reports.generation.response.SearchResponse;
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
		
		return citizenPlanRepository.fetchPlanStatus();
	}

	@Override
	public SearchResponse search(SearchRequest searchRequest) {
		
		CitizenPlan citizenPlan=new CitizenPlan();
		
		//BeanUtils.copyProperties(searchRequest, citizenPlan);
		
		ExampleMatcher ignoreCase = ExampleMatcher.matchingAll().withIgnoreCase();
		
		if(searchRequest.getPlanName()!=null && !"".equals(searchRequest.getPlanName()))
			citizenPlan.setPlanName(searchRequest.getPlanName());
		
		if(searchRequest.getPlanStatus()!=null && !"".equals(searchRequest.getPlanStatus()))
			citizenPlan.setStatus(searchRequest.getPlanStatus());
		
		if(searchRequest.getGender()!=null && !"".equals(searchRequest.getGender()))
			citizenPlan.setGender(searchRequest.getGender());
		
		if(searchRequest.getStartDate()!=null && !"".equals(searchRequest.getStartDate()))
			citizenPlan.setPlanStartDate(searchRequest.getStartDate());
		
		if(searchRequest.getEndDate()!=null && !"".equals(searchRequest.getEndDate()))
			citizenPlan.setPlanEndDate(searchRequest.getEndDate());	
		
		List<CitizenPlan> reports = this.citizenPlanRepository.findAll(Example.of(citizenPlan,ignoreCase));
		
		SearchResponse response=new SearchResponse();
		
		if(reports.isEmpty()) {
			response.setReports(reports);
		    response.setResponseMessage("No records are mathced with given criteria");
			return response;
			
		}
		else {
			response.setReports(reports);
		    response.setResponseMessage("Data Found");
		return response;
		}
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
