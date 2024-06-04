package com.reports.generation.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.reports.generation.entity.CitizenPlan;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {

	public void generatePdf(HttpServletResponse response,List<CitizenPlan> citizenPlans,File f) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();

		Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC);
		font.setSize(18);

		Paragraph paragraph = new Paragraph("Report Generation Pdf", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable pdfPTable = new PdfPTable(10 - 1);
		pdfPTable.addCell("Citizen_Id");
		pdfPTable.addCell("Citizen_Name");
		pdfPTable.addCell("Plan_Name");
		pdfPTable.addCell("Plan_Status");
		pdfPTable.addCell("plan_StartDate");
		pdfPTable.addCell("Plan_EndDate");
		pdfPTable.addCell("Benefit_amt");
		pdfPTable.addCell("Denied Reason");
		pdfPTable.addCell("Denied Date");
		
		for (CitizenPlan all : citizenPlans) {
			pdfPTable.addCell(String.valueOf(all.getCitizenId()));
			pdfPTable.addCell(all.getCitizenName());
			pdfPTable.addCell(all.getPlanName());
			pdfPTable.addCell(all.getStatus());
			if (all.getPlanStartDate() != null) {
				pdfPTable.addCell(all.getPlanStartDate() + "");
			} else {
				pdfPTable.addCell("N/A");
			}
			pdfPTable.addCell(all.getPlanEndDate() + "");
			pdfPTable.addCell(String.valueOf(all.getBenefitedAmt()));
			pdfPTable.addCell(all.getDenialReason());
			pdfPTable.addCell(all.getTerminatedDate() + "");
		}

		pdfPTable.setSpacingBefore(5);
		pdfPTable.setWidthPercentage(100f);

		document.add(pdfPTable);

		document.close();
	}
	
	
	
}
