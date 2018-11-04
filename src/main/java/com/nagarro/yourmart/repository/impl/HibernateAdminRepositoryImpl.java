package com.nagarro.yourmart.repository.impl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.yourmart.entity.Admin;
import com.nagarro.yourmart.repository.AdminRepository;

@Repository
public class HibernateAdminRepositoryImpl implements AdminRepository {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public String authenticate(String userId, String password) {
		String adminUserId = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
			Root<Admin> root = criteriaQuery.from(Admin.class);
			Predicate idPredicate = criteriaBuilder.equal(root.get("userId"), userId);
			Predicate passwordPredicate = criteriaBuilder.equal(root.get("password"), password);
			criteriaQuery.select(root.get("userId")).where(criteriaBuilder.and(idPredicate,passwordPredicate));
			Query<String> query = session.createQuery(criteriaQuery);
			adminUserId = query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return adminUserId;
	}

}
