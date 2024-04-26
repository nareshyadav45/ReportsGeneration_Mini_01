package com.reports.generation.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.reports.generation.entity.CitizenPlan;
import com.reports.generation.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CitizenPlanRepository citizenPlanRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		citizenPlanRepository.deleteAll();
		
		// cash data
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizenId(1);
		c1.setCitizenName("binod");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setStatus("Approved");
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setPlanStartDate(LocalDate.now());
		c1.setBenefitedAmt(5000.0);

		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("vinod");
		c2.setGender("Fe-Male");
		c2.setPlanName("Cash");
		c2.setStatus("Denied");
		c2.setDenialReason("Rent Income");

		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("cinod");
		c3.setGender("Male");
		c3.setPlanName("Cash");
		c3.setStatus("Terminated");
		c3.setBenefitedAmt(5000d);
		c3.setTerminationReason("Employed");
		c3.setTerminatedDate(LocalDate.now());
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));

		// Food data
		CitizenPlan c4 = new CitizenPlan();
		c4.setCitizenName("dinod");
		c4.setGender("Male");
		c4.setPlanName("food");
		c4.setStatus("Approved");
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setPlanStartDate(LocalDate.now());
		c4.setBenefitedAmt(5000.0);

		CitizenPlan c5 = new CitizenPlan();
		c5.setCitizenName("einod");
		c5.setGender("Fe-Male");
		c5.setPlanName("Food");
		c5.setStatus("Denied");
		c5.setDenialReason("Property Income");

		CitizenPlan c6 = new CitizenPlan();
		c6.setCitizenName("finod");
		c6.setGender("Male");
		c6.setPlanName("Food");
		c6.setStatus("Terminated");
		c6.setBenefitedAmt(5000d);
		c6.setTerminationReason("Employed");
		c6.setTerminatedDate(LocalDate.now());
		c6.setPlanStartDate(LocalDate.now().minusMonths(4));
		c6.setPlanEndDate(LocalDate.now().plusMonths(6));

		// Medical data
		CitizenPlan c7 = new CitizenPlan();
		c7.setCitizenName("ginod");
		c7.setGender("Male");
		c7.setPlanName("Medical");
		c7.setStatus("Approved");
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setPlanStartDate(LocalDate.now());
		c7.setBenefitedAmt(5000.0);

		CitizenPlan c8 = new CitizenPlan();
		c8.setCitizenName("einod");
		c8.setGender("Fe-Male");
		c8.setPlanName("meddical");
		c8.setStatus("Denied");
		c8.setDenialReason("House Income");

		CitizenPlan c9 = new CitizenPlan();
		c9.setCitizenName("jinod");
		c9.setGender("Male");
		c9.setPlanName("Medical");
		c9.setStatus("Terminated");
		c9.setBenefitedAmt(5000d);
		c9.setTerminationReason("Self Employed");
		c9.setTerminatedDate(LocalDate.now());
		c9.setPlanStartDate(LocalDate.now().minusMonths(4));
		c9.setPlanEndDate(LocalDate.now().plusMonths(6));

		// Employment data
		CitizenPlan c10 = new CitizenPlan();
		c10.setCitizenName("kinod");
		c10.setGender("Male");
		c10.setPlanName("Employment");
		c10.setStatus("Approved");
		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
		c10.setPlanStartDate(LocalDate.now());
		c10.setBenefitedAmt(5000.0);

		CitizenPlan c11 = new CitizenPlan();
		c11.setCitizenName("pinod");
		c11.setGender("Fe-Male");
		c11.setPlanName("Employment");
		c11.setStatus("Denied");
		c11.setDenialReason("Lagacy Income");

		CitizenPlan c12 = new CitizenPlan();
		c12.setCitizenName("ninod");
		c12.setGender("Male");
		c12.setPlanName("Employmnet");
		c12.setStatus("Terminated");
		c12.setBenefitedAmt(5000d);
		c12.setTerminationReason("Business");
		c12.setTerminatedDate(LocalDate.now());
		c12.setPlanStartDate(LocalDate.now().minusMonths(4));
		c12.setPlanEndDate(LocalDate.now().plusMonths(6));
		
		List<CitizenPlan> asList = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
		 
		citizenPlanRepository.saveAll(asList);
		
	}

}
