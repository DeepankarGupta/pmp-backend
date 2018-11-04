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

import com.nagarro.yourmart.entity.Seller;
import com.nagarro.yourmart.repository.SellerRepository;

@Repository
public class HibernateSellerRepositoryImpl implements SellerRepository {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public List<Seller> getAllSellers(String sortBy, String searchBy, String searchValue, Integer status, int offset,
			int limit) {
		List<Seller> sellers = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Seller> criteriaQuery = criteriaBuilder.createQuery(Seller.class);
			Root<Seller> root = criteriaQuery.from(Seller.class);
			
			List<Predicate> predicates = new ArrayList<>();
			
			if (searchBy != null && searchValue != null) {
				if (searchBy.equals("companyName") || searchBy.equals("ownerName") || searchBy.equals("phoneNumber")) {
					predicates.add(criteriaBuilder.like(root.get(searchBy), "%"+searchValue+"%"));					
				}
			}
			
			if (status != null) {
				predicates.add(criteriaBuilder.equal(root.get("status"), status));	
			}
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			predicates.toArray(predicatesArray);
			
			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.and(predicatesArray));
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy)));
			Query<Seller> query = session.createQuery(criteriaQuery);
			
			
		/*	
			String hql = "FROM Seller ";
			boolean isWhereRequired = true;

			if (searchBy != null && searchValue != null) {
				if (searchBy.equals("companyName") || searchBy.equals("ownerName") || searchBy.equals("phoneNumber")) {
					hql += "WHERE " + searchBy + " LIKE '%" + searchValue + "%' ";
					isWhereRequired = false;
				}
			}

			if (status != null) {

				int statusNumber = 0;
				if (status.equals("NEED_APPROVAL")) {
					statusNumber = 1;
				} else if (status.equals("APPROVED")) {
					statusNumber = 2;
				} else if (status.equals("REJECTED")) {
					statusNumber = 3;
				}

				if (statusNumber == 1 || statusNumber == 2 || statusNumber == 3) {
					if (isWhereRequired) {
						hql += "WHERE status = " + statusNumber + " ";
						isWhereRequired = false;
					} else {
						hql += "AND status = " + statusNumber + " ";
					}
				}

			}
			if(sortBy != null) {
				if (sortBy.equals("id") || sortBy.equals("createdAt")) {
					hql += "ORDER BY " + sortBy;
				}
			} else {
				hql += "ORDER BY status";
			}
			
			Query query = session.createQuery(hql);
			*/
			
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			sellers = query.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sellers;
	}

	@Override
	public Seller getSellerById(int id) {
		Seller seller = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			seller = session.find(Seller.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return seller;
	}

	@Override
	public Seller authenticate(int id, String password) {
		Seller seller = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Seller> criteriaQuery = criteriaBuilder.createQuery(Seller.class);
			Root<Seller> root = criteriaQuery.from(Seller.class);
			Predicate idPredicate = criteriaBuilder.equal(root.get("id"), id);
			Predicate passwordPredicate = criteriaBuilder.equal(root.get("password"), password);
			criteriaQuery.select(root).where(criteriaBuilder.and(idPredicate,passwordPredicate));
			Query<Seller> query = session.createQuery(criteriaQuery);
			seller = query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return seller;
	}

	@Override
	public void addNewSeller(Seller seller) {
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			session.beginTransaction();
			session.save(seller);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Seller changeSellerStatus(int sellerId, int status) {
		Seller seller = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			session.beginTransaction();
			seller = session.find(Seller.class, sellerId);
			seller.setStatus(status);
			session.update(seller);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return seller;
	}

	@Override
	public List<Integer> getAllSellerIds() {
		List<Integer> sellerIds = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			CriteriaQuery<Integer> criteriaQuery = session.getCriteriaBuilder().createQuery(Integer.class);
			Root<Seller> root = criteriaQuery.from(Seller.class);
			criteriaQuery.select(root.get("id"));
			Query<Integer> query = session.createQuery(criteriaQuery);
			sellerIds = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sellerIds;
	}

	@Override
	public List<String> getAllSellerCompanyNames() {
		List<String> sellerCompanyNames = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			CriteriaQuery<String> criteriaQuery = session.getCriteriaBuilder().createQuery(String.class);
			Root<Seller> root = criteriaQuery.from(Seller.class);
			criteriaQuery.select(root.get("companyName"));
			Query<String> query = session.createQuery(criteriaQuery);
			sellerCompanyNames = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sellerCompanyNames;
	}

	@Override
	public Integer getSellerIdByToken(String token) {
		Integer sellerId = null;
		try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
			Root<Seller> root = criteriaQuery.from(Seller.class);
			criteriaQuery.select(root.get("id"));
			criteriaQuery.where(criteriaBuilder.equal(root.get("token"), token));
			Query<Integer> query = session.createQuery(criteriaQuery);
			sellerId = query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sellerId;
	}

}
