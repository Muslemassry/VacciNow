package com.vaccnow.healthportal.endpoint;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaccnow.healthportal.Constants;
import com.vaccnow.healthportal.model.Application;
import com.vaccnow.healthportal.reponse.MessageResponse;
import com.vaccnow.healthportal.request.ApplicationRequestObj;
import com.vaccnow.healthportal.service.VaccNowService;
import com.vaccnow.healthportal.utils.MailUtil;


@RestController
public class VaccNowEndpoint {
	
	@Autowired
	private VaccNowService vaccNowService;
	
	@RequestMapping(path = "/allBranches")
	public ResponseEntity<?> listAllBranches() {
		List l = vaccNowService.listAllBranches();
		return ResponseEntity.ok(l);
	}
	
	@RequestMapping(path = "/listAvailableVaccinesBranch/{branchId}")
	public ResponseEntity<?> listAvailableVaccinesBranch(@PathVariable Integer branchId) {
		List l = vaccNowService.listAvailableVaccinePerBranches(branchId);
		return ResponseEntity.ok(l);
	}
	
	@RequestMapping(path = "/availableTimeForBranch/{branchId}")
	public ResponseEntity<?> availableTimeForBranch(@PathVariable Integer branchId) {
		List<Application> l = vaccNowService.listAllApplicationsPerBranches(branchId);
		List<Date> branchTimes = l.stream().map(x -> x.getAppointment()).collect(Collectors.toList());
		List<Date> timeSlots = Constants.TIMES_SLOTS.stream().collect(Collectors.toList());
		timeSlots.removeIf(e -> branchTimes.contains(e));
		return ResponseEntity.ok(timeSlots);
	}
	
	@RequestMapping(path = "/listAllAppliedVaccinationPerBranch/{branchId}")
	public ResponseEntity<?> listAllAppliedVaccinationPerBranch(@PathVariable Integer branchId) {
		List l = vaccNowService.listAppliedVaccinePerBranches(branchId);
		return ResponseEntity.ok(l);
	}
	
	@RequestMapping(path = "/listAllAppliedVaccinationPerPeriod")
	public ResponseEntity<?> listAllAppliedVaccinationPerPeriod(@RequestParam(name = "fromDt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDt, @RequestParam(name = "toDt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDt) {
		List l = vaccNowService.listAllAppliedApplicationsPerPerion(fromDt, toDt);
		return ResponseEntity.ok(l);
	}
	
	@RequestMapping(path = "/showAllConfirmedVaccinationsOverPeriod")
	public ResponseEntity<?> showAllConfirmedVaccinationsOverPeriod(@RequestParam(name = "fromDt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDt, @RequestParam(name = "toDt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDt) {
		List l = vaccNowService.listAllConfirmedApplications(fromDt, toDt);
		return ResponseEntity.ok(l);
	}
	
	@RequestMapping(path = "/vaccineCertificate", method = RequestMethod.PUT)
	public ResponseEntity<?> vaccineCertificate(@RequestBody ApplicationRequestObj applicationRequestObj) {
		Application confirmedApplication = vaccNowService.confirmVaccination(applicationRequestObj);
		MailUtil.sendEmail(applicationRequestObj.getToEmail(), confirmedApplication);
		return ResponseEntity.ok(confirmedApplication);
	}
	
	@RequestMapping(path = "/scheduleVaccinationTimeslot", method = RequestMethod.POST)
	public ResponseEntity<?> scheduleVaccinationTimeslot(@RequestBody ApplicationRequestObj applicationRequestObj) {
		Integer id = vaccNowService.scheduleVaccinationTimeslot(applicationRequestObj);
		return ResponseEntity.ok(new MessageResponse("the Schedule Id is: " + id));
	}

	public VaccNowService getVaccNowService() {
		return vaccNowService;
	}

	public void setVaccNowService(VaccNowService vaccNowService) {
		this.vaccNowService = vaccNowService;
	}
}
