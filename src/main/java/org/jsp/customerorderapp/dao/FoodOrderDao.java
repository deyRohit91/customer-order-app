package org.jsp.customerorderapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jsp.customerorderapp.dto.Customer;
import org.jsp.customerorderapp.dto.FoodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodOrderDao {
	@Autowired
	private EntityManager entityManager;

	public FoodOrder saveOrder(FoodOrder order, int customer_id) {
		EntityTransaction transaction = entityManager.getTransaction();
		Customer customer = entityManager.find(Customer.class, customer_id);
		if (customer != null) {
			customer.getFoodOrders().add(order);
			order.setCustomer(customer);
			entityManager.persist(order);
			transaction.begin();
			transaction.commit();
			return order;
		}
		return null;
	}

	public FoodOrder updateOrder(FoodOrder order) {
		EntityTransaction transaction = entityManager.getTransaction();
		FoodOrder dbOrder = entityManager.find(FoodOrder.class, order.getId());
		if (dbOrder != null) {
			dbOrder.setAddress(order.getAddress());
			dbOrder.setCost(order.getCost());
			dbOrder.setFoodItem(order.getFoodItem());
			transaction.begin();
			transaction.commit();
			return dbOrder;
		}
		return null;
	}

	public FoodOrder findById(int id) {
		return entityManager.find(FoodOrder.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<FoodOrder> findByCustomerId(int id) {
		return entityManager.createQuery("select c.foodOrders from Customer c where c.id=?1").setParameter(1, id)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<FoodOrder> findByCustomer(long phone, String password) {
		return entityManager.createQuery("select c.foodOrders from Customer c where c.phone=?1 and c.password=?2")
				.setParameter(1, phone).setParameter(2, password).getResultList();
	}

}
