package com.reports.generation.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.reports.generation.entity.CitizenPlan;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {

	public void generateExcel(HttpServletResponse response, List<CitizenPlan> allRecords,File f) throws Exception{

		Workbook workbook = new HSSFWorkbook();

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

		int dataRowIndex = 1;

		for (CitizenPlan plans : allRecords) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plans.getCitizenId());
			dataRow.createCell(1).setCellValue(plans.getCitizenName());
			dataRow.createCell(2).setCellValue(plans.getGender());
			dataRow.createCell(3).setCellValue(plans.getPlanName());
			dataRow.createCell(4).setCellValue(plans.getStatus());
			if (plans.getPlanStartDate() != null) {
				dataRow.createCell(5).setCellValue(plans.getPlanStartDate() + "");
			} else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			if (plans.getPlanEndDate() != null) {
				dataRow.createCell(6).setCellValue(plans.getPlanEndDate() + "");
			} else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			dataRow.createCell(7).setCellValue(plans.getTerminationReason());
			dataRow.createCell(8).setCellValue(plans.getTerminatedDate());
			if (plans.getBenefitedAmt() != null) {
				dataRow.createCell(9).setCellValue(plans.getBenefitedAmt());
			} else {
				dataRow.createCell(9).setCellValue("N/A");
			}
			dataRow.createCell(10).setCellValue(plans.getDenialReason());

			dataRowIndex++;

		}
		
		FileOutputStream fileOutputStream=new FileOutputStream(f);
		workbook.write(fileOutputStream);
		fileOutputStream.close();
		
		

		try (ServletOutputStream outputStream = response.getOutputStream()) {
			workbook.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while writing Excel to output stream");
		} finally {
			workbook.close();
		}
	}
}
