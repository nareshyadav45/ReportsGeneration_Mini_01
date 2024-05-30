package com.reports.generation.service.impl;

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

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

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
	public boolean exportDataExcel(HttpServletResponse response) throws Exception {
		List<CitizenPlan> allRecords = this.citizenPlanRepository.findAll();
		
		Workbook workbook=new HSSFWorkbook();
		
		Sheet sheet = workbook.createSheet("plans-data");
		
		Row headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("Citizen_Id");
		headerRow.createCell(1).setCellValue("Citizen_Name");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plan_Name");
		headerRow.createCell(4).setCellValue("Plan_Status");
		headerRow.createCell(5).setCellValue("Plan_StartDate");
		headerRow.createCell(6).setCellValue("Plan_EndDate");
		headerRow.createCell(7).setCellValue("Termination_Reason");
		headerRow.createCell(8).setCellValue("Termination_Date");
		headerRow.createCell(9).setCellValue("Benefit_Amount");
		headerRow.createCell(10).setCellValue("Denial_Reason");
		
		int dataRowIndex=1;
		
		for(CitizenPlan plans:allRecords) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plans.getCitizenId());
			dataRow.createCell(1).setCellValue(plans.getCitizenName());
			dataRow.createCell(2).setCellValue(plans.getGender());
			dataRow.createCell(3).setCellValue(plans.getPlanName());
			dataRow.createCell(4).setCellValue(plans.getStatus());
			if(plans.getPlanStartDate()!=null) {
			dataRow.createCell(5).setCellValue(plans.getPlanStartDate()+"");
			}else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			if(plans.getPlanEndDate()!=null) {
			dataRow.createCell(6).setCellValue(plans.getPlanEndDate()+"");
			}else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			dataRow.createCell(7).setCellValue(plans.getTerminationReason());
			dataRow.createCell(8).setCellValue(plans.getTerminatedDate());
			if(plans.getBenefitedAmt()!=null) {
			dataRow.createCell(9).setCellValue(plans.getBenefitedAmt());
			}else {
				dataRow.createCell(9).setCellValue("N/A");
			}
			dataRow.createCell(10).setCellValue(plans.getDenialReason());
			
			dataRowIndex++;
			
		}
		
	
		  try (ServletOutputStream outputStream = response.getOutputStream()) {
		        workbook.write(outputStream);
		        outputStream.flush();
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw new Exception("Error while writing Excel to output stream");
		    } finally {
		        workbook.close();
		    }
		
	return true;
	}

	@Override
	public boolean exportDataPdf(HttpServletResponse response) throws Exception {
		
	Document document =new Document(PageSize.A4);
	PdfWriter.getInstance(document, response.getOutputStream());
	document.open();
	
	Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC);
	font.setSize(18);
	
	Paragraph paragraph =new Paragraph("Report Generation Pdf",font);
	paragraph.setAlignment(Paragraph.ALIGN_CENTER);
	
	document.add(paragraph);
	
	PdfPTable pdfPTable =new PdfPTable(10-1);
	pdfPTable.addCell("Citizen_Id");
	pdfPTable.addCell("Citizen_Name");
	pdfPTable.addCell("Plan_Name");
	pdfPTable.addCell("Plan_Status");
	pdfPTable.addCell("plan_StartDate");
	pdfPTable.addCell("Plan_EndDate");
	pdfPTable.addCell("Benefit_amt");
    pdfPTable.addCell("Denied Reason");
    pdfPTable.addCell("Denied Date");
    
    List<CitizenPlan> allRecords = this.citizenPlanRepository.findAll();
    
    for(CitizenPlan all:allRecords) {
    	pdfPTable.addCell(String.valueOf(all.getCitizenId()));
    	pdfPTable.addCell(all.getCitizenName());
    	pdfPTable.addCell(all.getPlanName());
    	pdfPTable.addCell(all.getStatus());
    	if(all.getPlanStartDate()!=null) {
        pdfPTable.addCell(all.getPlanStartDate()+"");
    	}else {
    		pdfPTable.addCell("N/A");
    	}
        pdfPTable.addCell(all.getPlanEndDate()+"");
        pdfPTable.addCell(String.valueOf(all.getBenefitedAmt()));
        pdfPTable.addCell(all.getDenialReason());
        pdfPTable.addCell(all.getTerminatedDate()+"");	
    }
    
    pdfPTable.setSpacingBefore(5);
    pdfPTable.setWidthPercentage(100f);
    
    
    document.add(pdfPTable);
	
	document.close();
		return true;
	}

	

}
