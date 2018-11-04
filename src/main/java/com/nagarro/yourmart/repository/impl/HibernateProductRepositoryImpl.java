package com.nagarro.yourmart.repository.impl;

import java.util.ArrayList;
import java.util.List;

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

import com.nagarro.yourmart.entity.Product;
import com.nagarro.yourmart.repository.ProductRepository;

@Repository
public class HibernateProductRepositoryImpl implements ProductRepository {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public List<Product> getAllProducts(String sortBy, String searchBy, String searchValue, Integer status,
			Integer category, Integer id, int offset, int limit) {

		List<Product> products = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
			Root<Product> root = criteriaQuery.from(Product.class);

			List<Predicate> predicates = new ArrayList<>();

			predicates.add(criteriaBuilder.equal(root.get("seller").get("id"), id));

			if (searchBy != null && (searchBy.equals("code") || searchBy.equals("name"))) {
				predicates.add(criteriaBuilder.like(root.get(searchBy), "%" + searchValue + "%"));
			}

			if (status != null) {
				predicates.add(criteriaBuilder.equal(root.get("status"), status));
			}

			if (category != null) {
				predicates.add(criteriaBuilder.equal(root.get("category").get("id"), category));
			}

			if (sortBy == null || (!sortBy.equals("code") && !sortBy.equals("name") && !sortBy.equals("mrp") && !sortBy.equals("ssp")
					&& !sortBy.equals("ymp"))) {
				sortBy = "id";
			}

			Predicate[] predicatesArray = new Predicate[predicates.size()];
			predicates.toArray(predicatesArray);

			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.and(predicatesArray));
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy)));
			Query<Product> query = session.createQuery(criteriaQuery);

			query.setFirstResult(offset);
			query.setMaxResults(limit);
			products = query.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return products;
	}

	@Override
	public List<Product> getAllProducts(String sortBy, String searchBy, String searchValue, Integer status,
			Integer category, Integer sellerId, String sellerCompanyName, int offset, int limit) {
		
		List<Product> products = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
			Root<Product> root = criteriaQuery.from(Product.class);

			List<Predicate> predicates = new ArrayList<>();

			if (searchBy != null && (searchBy.equals("code") || searchBy.equals("name"))) {
				predicates.add(criteriaBuilder.like(root.get(searchBy), "%" + searchValue + "%"));
			}

			if (status != null) {
				predicates.add(criteriaBuilder.equal(root.get("status"), status));
			}

			if (category != null) {
				predicates.add(criteriaBuilder.equal(root.get("category").get("id"), category));
			}
			
			if (sellerId != null) {
				predicates.add(criteriaBuilder.equal(root.get("seller").get("id"), sellerId));
			}
			
			if (sellerCompanyName != null) {
				predicates.add(criteriaBuilder.equal(root.get("seller").get("companyName"), sellerCompanyName));
			}

			if (sortBy == null || (!sortBy.equals("code") && !sortBy.equals("name") && !sortBy.equals("mrp") && !sortBy.equals("ssp")
					&& !sortBy.equals("ymp"))) {
				sortBy = "id";
			}

			System.out.println("hgfjfgjh: " + predicates.size());
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			predicates.toArray(predicatesArray);

			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.and(predicatesArray));
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy)));
			Query<Product> query = session.createQuery(criteriaQuery);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			products = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return products;
	}

	@Override
	public Product getProductById(int id) {
		Product product = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			product = session.find(Product.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return product;
	}

	@Override
	public Product addNewProduct(Product product) {
		Product newProduct = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			session.beginTransaction();
			Integer newProductId = (Integer) session.save(product);
			session.getTransaction().commit();
			newProduct = getProductById(newProductId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return newProduct;
	}

	@Override
	public Product updateProduct(Product product) {
		Product updatedProduct = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			session.beginTransaction();
			updatedProduct = (Product) session.merge(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return updatedProduct;
	}

	@Override
	public Product deleteProduct(int id) {
		Product product = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			session.beginTransaction();
			product = session.find(Product.class, id);
			if (product != null) {
				session.delete(product);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return product;
	}

	@Override
	public Product changeProductStatus(int productId, int status) {
		Product product = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			session.beginTransaction();
			product = session.find(Product.class, productId);
			product.setStatus(status);
			session.update(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return product;
	}

	@Override
	public Product addComment(int id, String comment) {
		Product product = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			session.beginTransaction();
			product = session.find(Product.class, id);
			product.setComment(comment);
			session.update(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return product;
	}

	@Override
	public Integer getSellerId(int productId) {
		Integer sellerId = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
			Root<Product> root = criteriaQuery.from(Product.class);
			criteriaQuery.select(root.get("seller").get("id"));
			criteriaQuery.where(criteriaBuilder.equal(root.get("id"), productId));
			Query<Integer> query = session.createQuery(criteriaQuery);
			sellerId = query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sellerId;
	}

}
