package com.saraya.FirstOrmDemo.repository;
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.saraya.FirstOrmDemo.entity.Customer;

@Repository("customerRepository")
public class CustomerDAOImpl implements CustomerDAO {
	private EntityManagerFactory entityManagerFactory;
	@PersistenceUnit
	public void setEntityManagerFactory (EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	@Override
	public void insert(Customer customer) {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
	}
	@Override
	public int remove(Long phoneNo) {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		int result = 0;
		try {
			Customer emp = entityManager.find(Customer.class, phoneNo);
			entityManager.remove(emp);
			result = 1;
			entityManager.getTransaction().commit();
		} catch (Exception exp) {
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		return result;
	}
    @Override
	@SuppressWarnings("unchecked")
	public List<Customer> getAll() {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("Select c from Customer c");
		return (List<Customer>)query.getResultList();
	}
	@Override
	public void update(Long phoneNo, String address) {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Customer cust =  entityManager.find(Customer.class, phoneNo);
		cust.setAddress(address);
		entityManager.getTransaction().commit();
	}
}
