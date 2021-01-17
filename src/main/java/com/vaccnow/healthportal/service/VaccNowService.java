package com.vaccnow.healthportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaccnow.healthportal.Constants;
import com.vaccnow.healthportal.dao.BaseDao;
import com.vaccnow.healthportal.model.Application;
import com.vaccnow.healthportal.model.BaseEntity;
import com.vaccnow.healthportal.model.Branch;
import com.vaccnow.healthportal.model.Patient;
import com.vaccnow.healthportal.model.Vaccine;
import com.vaccnow.healthportal.request.ApplicationRequestObj;
import com.vaccnow.healthportal.utils.Util;

@Service
public class VaccNowService {
	
	@Autowired
	private BaseDao baseDao;
	
	public List<Branch> listAllBranches() {
		return baseDao.findAll(Branch.class);
	}
	
	public void save(BaseEntity entity) {
		baseDao.save(entity);
	}
	
	public Integer scheduleVaccinationTimeslot(ApplicationRequestObj applicationRequestOb) {
		Patient p = new Patient(applicationRequestOb.patientId);
		Branch b = new Branch(applicationRequestOb.branchId);
		Vaccine v = new Vaccine(applicationRequestOb.vaccineId);
		p = (Patient) baseDao.find(p);
		b = (Branch) baseDao.find(b);
		v = (Vaccine) baseDao.find(v);
		v.setStatus(Constants.APPLICATION_STATUS_APPLIED);
		baseDao.merge(v);
		Application application = new Application(applicationRequestOb.getPaymentType(), applicationRequestOb.getAppointment(), p, b, v);
		Object id = baseDao.save(application);
		return (Integer) id;
	}
	
	public List<Vaccine> listAvailableVaccinePerBranches(Integer branchId) {
		return baseDao.listBranchVaccines(branchId, Constants.APPLICATION_STATUS_AVAILABLE);
	}
	
	public List<Vaccine> listAppliedVaccinePerBranches(Integer branchId) {
		return baseDao.listBranchVaccines(branchId, Constants.APPLICATION_STATUS_APPLIED);
	}
	
	public List<Application> listAllApplicationsPerBranches(Integer branchId) {
		return baseDao.listBranchTimes(branchId, Util.atStartOfDay(new Date()), Util.atEndOfDay(new Date()));
	}
	
	public List<Application> listAllAppliedApplicationsPerPerion(Date from, Date to) {
		return baseDao.listApplication(Constants.APPLICATION_STATUS_APPLIED, from, to);
	}
	
	public List<Application> listAllConfirmedApplications(Date from, Date to) {
		return baseDao.listApplication(Constants.APPLICATION_STATUS_CONFIRMED, from, to);
	}
	
	public Application confirmVaccination(ApplicationRequestObj applicationRequestOb) {
		Application a = new Application();
		a.setId(applicationRequestOb.getId());
		Object applicationObj = baseDao.find(a);
		if (applicationObj == null || !((Application) applicationObj).getVaccine().getStatus().equals(Constants.APPLICATION_STATUS_APPLIED)) {
			return null;
		} else {
			a = (Application) applicationObj;
			a.getVaccine().setStatus(Constants.APPLICATION_STATUS_CONFIRMED);
			a.getVaccine().setVaccinationDate(new Date());
			baseDao.merge(a);
			return a; // here is certificate
		}
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	
}
