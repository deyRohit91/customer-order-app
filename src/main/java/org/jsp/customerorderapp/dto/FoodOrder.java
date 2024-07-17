package org.jsp.customerorderapp.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class FoodOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String foodItem;
	@Column(nullable = false)
	private double cost;
	@CreationTimestamp
	private LocalDateTime orderedTime;
	@UpdateTimestamp
	private LocalDateTime deliveryTime;
	@Column(nullable = false)
	private String address;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Override
	public String toString() {
		return "FoodOrder [id=" + id + ", foodItem=" + foodItem + ", cost=" + cost + ", orderedTime=" + orderedTime
				+ ", deliveryTime=" + deliveryTime + ", address=" + address + "]";
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public LocalDateTime getOrderedTime() {
		return orderedTime;
	}

	public void setOrderedTime(LocalDateTime orderedTime) {
		this.orderedTime = orderedTime;
	}

	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
