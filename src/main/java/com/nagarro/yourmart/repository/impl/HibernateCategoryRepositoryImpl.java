package com.nagarro.yourmart.repository.impl;

import java.util.ArrayList;
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

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.nagarro.yourmart.dto.CategoryCountResponse;
import com.nagarro.yourmart.entity.Category;
import com.nagarro.yourmart.entity.Product;
import com.nagarro.yourmart.repository.CategoryRepository;

@Repository
public class HibernateCategoryRepositoryImpl implements CategoryRepository {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public List<Category> getAllCategories(int offset, int limit) {
		List<Category> categories = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
			Root<Category> root = criteriaQuery.from(Category.class);
			criteriaQuery.select(root);
			Query<Category> query = session.createQuery(criteriaQuery);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			categories = query.list();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return categories;
	}
	
	@Override
	public Category getCategoryById(int id) {
		Category category = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			category = session.find(Category.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return category;
	}

	@Override
	public Category addNewCategory(Category category) {
		Category newCategory = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			session.beginTransaction();
			Integer newCategoryId = (Integer) session.save(category);
			session.getTransaction().commit();
			newCategory = getCategoryById(newCategoryId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return newCategory;
	}

	@Override
	public Category updateCategory(Category category) {
		Category updatedCategory = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			session.beginTransaction();
			updatedCategory = (Category) session.merge(category);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return updatedCategory;
	}

	@Override
	public Category deleteCategory(int id) {
		Category category = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			session.beginTransaction();
			category = session.find(Category.class, id);
			if (category != null) {
				session.delete(category);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return category;
	}

	@Override
	public List<CategoryCountResponse> getAllCategoriesWithCount(int offset, int limit) {
		List<CategoryCountResponse> categories = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Product> root = criteriaQuery.from(Product.class);
			criteriaQuery.multiselect(root.get("category").get("id"), 
									  root.get("category").get("name"),
									  criteriaBuilder.count(root.get("id")));
			criteriaQuery.groupBy(root.get("category").get("id"));
			Query<Object[]> query = session.createQuery(criteriaQuery);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Object[]> objects = query.list();
			categories = new ArrayList<>();
			CategoryCountResponse category;
			for (Object[] object : objects) {
				category = new CategoryCountResponse();
				category.setId((int) object[0]);
				category.setName((String) object[1]);
				category.setProductCount((Long) object[2]);
				categories.add(category);
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return categories;
	}

}
