package com.reports.generation.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.reports.generation.entity.CitizenPlan;
import com.reports.generation.repo.CitizenPlanRepository;
import com.reports.generation.request.SearchRequest;
import com.reports.generation.response.SearchResponse;
import com.reports.generation.service.ReportService;
import com.reports.generation.util.EmailSender;
import com.reports.generation.util.ExcelGenerator;
import com.reports.generation.util.PdfGenerator;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository citizenPlanRepository;

	@Autowired
	private ExcelGenerator excelGenerator;

	@Autowired
	private PdfGenerator pdfGenerator;

	@Autowired
	private EmailSender emailSender;

	@Override
	public List<String> getPlanNames() {

		List<String> fetchPlanNames = citizenPlanRepository.fetchPlanNames();

		return fetchPlanNames;
	}

	@Override
	public List<String> getPlanStatuses() {

		return citizenPlanRepository.fetchPlanStatus();
	}

	@Override
	public SearchResponse search(SearchRequest searchRequest) {

		CitizenPlan citizenPlan = new CitizenPlan();

		// BeanUtils.copyProperties(searchRequest, citizenPlan);

		ExampleMatcher ignoreCase = ExampleMatcher.matchingAll().withIgnoreCase();

		if (searchRequest.getPlanName() != null && !"".equals(searchRequest.getPlanName()))
			citizenPlan.setPlanName(searchRequest.getPlanName());

		if (searchRequest.getPlanStatus() != null && !"".equals(searchRequest.getPlanStatus()))
			citizenPlan.setStatus(searchRequest.getPlanStatus());

		if (searchRequest.getGender() != null && !"".equals(searchRequest.getGender()))
			citizenPlan.setGender(searchRequest.getGender());

		if (searchRequest.getStartDate() != null && !"".equals(searchRequest.getStartDate()))
			citizenPlan.setPlanStartDate(searchRequest.getStartDate());

		if (searchRequest.getEndDate() != null && !"".equals(searchRequest.getEndDate()))
			citizenPlan.setPlanEndDate(searchRequest.getEndDate());

		List<CitizenPlan> reports = this.citizenPlanRepository.findAll(Example.of(citizenPlan, ignoreCase));

		SearchResponse response = new SearchResponse();

		if (reports.isEmpty()) {
			response.setReports(reports);
			response.setResponseMessage("No records are mathced with given criteria");
			return response;

		} else {
			response.setReports(reports);
			response.setResponseMessage("Data Found");
			return response;
		}
	}

	@Override
	public boolean exportDataExcel(HttpServletResponse response) throws Exception {

		File f = new File("plans.xls");

		List<CitizenPlan> allRecords = this.citizenPlanRepository.findAll();
		excelGenerator.generateExcel(response, allRecords, f);

		String subject = "Mail Sending Test Sample";
		String body = "<h2>Successfully Mail Sent Using Java Rest Api</h2>";
		String to = "yadavtest45@gmail.com";
		String[] mails = { "yadavtest45@gmail.com", "sreekanthpininti466@gmail.com", "vivekkumar706277@gmail.com" };

		boolean mailSender = this.emailSender.mailSender(subject, body, mails, f);

		f.delete();

		return true;
	}

	@Override
	public boolean exportDataPdf(HttpServletResponse response) throws Exception {
		List<CitizenPlan> allRecords = this.citizenPlanRepository.findAll();

		File f = new File("plans_data.pdf");

		this.pdfGenerator.generatePdf(response, allRecords, f);

		String subject = "Mail Sending Test Sample";
		String body = "<h2>Successfully Mail Sent Using Java Rest Api</h2>";
		String[] to = { "yadavtest45@gmail.com", "sreekanthpininti466@gmail.com" };

		boolean mailSender = this.emailSender.mailSender(subject, body, to, f);
		f.delete();

		return true;
	}

}
