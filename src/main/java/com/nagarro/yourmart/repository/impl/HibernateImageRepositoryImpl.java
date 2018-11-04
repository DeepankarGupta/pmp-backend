package com.nagarro.yourmart.repository.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.yourmart.entity.Image;
import com.nagarro.yourmart.repository.ImageRepository;

@Repository
public class HibernateImageRepositoryImpl implements ImageRepository {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void uploadImage(Image image) {
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			session.beginTransaction();
			session.save(image);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Image> getAllProductImages(int productId) {
		List<Image> images = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Image> criteriaQuery = criteriaBuilder.createQuery(Image.class);
			Root<Image> root = criteriaQuery.from(Image.class);
			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.equal(root.get("product").get("id"), productId));
			Query<Image> query = session.createQuery(criteriaQuery);
			images = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return images;
	}
	
	
	
	

}
