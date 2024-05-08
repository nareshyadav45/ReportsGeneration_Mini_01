package com.reports.generation.response;

import java.util.List;
import java.util.Objects;

import com.reports.generation.entity.CitizenPlan;

public class SearchResponse {

	private List<CitizenPlan>reports;
	private String responseMessage;
	public List<CitizenPlan> getReports() {
		return reports;
	}
	public void setReports(List<CitizenPlan> reports) {
		this.reports = reports;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	@Override
	public int hashCode() {
		return Objects.hash(reports, responseMessage);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchResponse other = (SearchResponse) obj;
		return Objects.equals(reports, other.reports) && Objects.equals(responseMessage, other.responseMessage);
	}
	public SearchResponse(List<CitizenPlan> reports, String responseMessage) {
		super();
		this.reports = reports;
		this.responseMessage = responseMessage;
	}
	public SearchResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
