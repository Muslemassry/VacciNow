package com.vaccnow.healthportal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.vaccnow.healthportal.service.VaccNowService;
import com.vaccnow.healthportal.utils.Util;

@SpringBootTest
@AutoConfigureMockMvc
class HealthportalApplicationTests {

	@Test
	void contextLoads() {
		assertThat(vaccNowService).isNotNull();
	}
	
	@Test
	void listAllBranches_test() {
        List branches = vaccNowService.listAllBranches();
        assertEquals(3, branches.size());
	}
	
	@Test
	void listAvailableVaccinePerBranches_test() {
        List branches = vaccNowService.listAvailableVaccinePerBranches(1);
        assertEquals(2, branches.size());
	}
	
	@Test
	void listAppliedVaccinePerBranches_test() {
        List branches = vaccNowService.listAppliedVaccinePerBranches(1);
        assertEquals(0, branches.size());
	}
	
	@Test
	void listAllTimePerBranches_test() {
        List branches = vaccNowService.listAllApplicationsPerBranches(1);
        assertEquals(0, branches.size());
	}
	
	@Test
	void listAllAppliedApplicationsPerPerion_test() {
        List branches = vaccNowService.listAllAppliedApplicationsPerPerion(Util.atStartOfDay(new Date()), Util.atEndOfDay(new Date()));
        assertEquals(0, branches.size());
	}
	
	@Test
	void listAllConfirmedApplications_test() {
		Long beginingOfYear = (new Date()).getTime() - (30*24*60*60*1000*2);
        List branches = vaccNowService.listAllConfirmedApplications(Util.atStartOfDay(new Date(beginingOfYear)), Util.atEndOfDay(new Date()));
        assertEquals(1, branches.size());
	}
	
	@Autowired
    private VaccNowService vaccNowService;
}
