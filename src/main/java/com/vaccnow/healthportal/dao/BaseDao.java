package com.vaccnow.healthportal.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vaccnow.healthportal.model.BaseEntity;


@Repository
public class BaseDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List findAll(Class c) {
		List l = null;
		Session session  = sessionFactory.openSession();
		l = session.createQuery("SELECT a FROM " + c.getSimpleName() + " a", c).list();
		Hibernate.initialize(l);
		session.close();
		return l;
	}
	
	public Object save(BaseEntity entity) {
		Object savedObj = null;
		Session session  = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		savedObj = session.save(entity);
		tx.commit();
		session.close();
		return savedObj;
	}
	
	public void merge(BaseEntity entity) {
		Session session  = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.merge(entity);
		tx.commit();
		session.close();
	}
	
	public BaseEntity find(BaseEntity entity) {
		BaseEntity bE = null;
		Session session  = sessionFactory.openSession();
		bE = session.find(entity.getClass(), entity.getId());
		session.close();
		return bE;
	}
	
	public List listBranchVaccines(Integer branchId, String status) {
		List l = null;
		Session session  = sessionFactory.openSession();
		l = session.createQuery("SELECT a FROM Vaccine a WHERE a.branch.id = :bId AND a.status = :status").setParameter("bId", branchId).setParameter("status", status).list();
		session.close();
		return l;
	}
	
	public List listBranchTimes(Integer branchId, Date stDate, Date edDate) {
		List l = null;
		Session session  = sessionFactory.openSession();
		l = session.createQuery("SELECT a FROM Application a WHERE a.branch.id = :bId AND a.appointment BETWEEN :stDate AND :edDate").setParameter("bId", branchId).setParameter("stDate", stDate).setParameter("edDate", edDate).list();
		session.close();
		return l;
	}
	
	public List listApplication(Date stDate, Date edDate) {
		List l = null;
		Session session  = sessionFactory.openSession();
		l = session.createQuery("SELECT a FROM Application a WHERE a.appointment BETWEEN :stDate AND :edDate").setParameter("stDate", stDate).setParameter("edDate", edDate).list();
		session.close();
		return l;
	}
	
	public List listApplication(String vaccineStatus, Date stDate, Date edDate) {
		List l = null;
		Session session  = sessionFactory.openSession();
		l = session.createQuery("SELECT a FROM Application a WHERE a.vaccine.status = :vaccineStatus AND a.appointment BETWEEN :stDate AND :edDate").setParameter("vaccineStatus", vaccineStatus).setParameter("stDate", stDate).setParameter("edDate", edDate).list();
		session.close();
		return l;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
