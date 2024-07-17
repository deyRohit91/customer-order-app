package org.jsp.customerorderapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.customerorderapp.CustomerOrderConfig;
import org.jsp.customerorderapp.dao.FoodOrderDao;
import org.jsp.customerorderapp.dto.FoodOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FoodOrderController {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ApplicationContext context = new AnnotationConfigApplicationContext(CustomerOrderConfig.class);
		FoodOrderDao foodOrderDao = context.getBean("foodOrderDao", FoodOrderDao.class);
		System.out.println("1.Place an Order");
		System.out.println("2.Update an Order");
		System.out.println("3.Find Order By Id");
		System.out.println("4.Find Orders By Customer Id");
		System.out.println("5.Find Orders by Customer Phone and Password");
		switch (sc.nextInt()) {
		case 1: {
			System.out.println("Enter Customer Id, Food-Item, cost and address to place an Order");
			int customer_id = sc.nextInt();
			FoodOrder foodOrder = new FoodOrder();
			foodOrder.setFoodItem(sc.next());
			foodOrder.setCost(sc.nextDouble());
			foodOrder.setAddress(sc.next());
			foodOrder = foodOrderDao.saveOrder(foodOrder, customer_id);
			if (foodOrder != null)
				System.out.println("Order placed and Your Order Id is:" + foodOrder.getId());
			else
				System.err.println("cannot place an Order as Customer Id is Invalid");
			break;
		}
		case 2: {
			System.out.println("Enter  Id, Food-Item, cost and address to place an Order");
			FoodOrder foodOrder = new FoodOrder();
			foodOrder.setId(sc.nextInt());
			foodOrder.setFoodItem(sc.next());
			foodOrder.setCost(sc.nextDouble());
			foodOrder.setAddress(sc.next());
			foodOrder = foodOrderDao.updateOrder(foodOrder);
			if (foodOrder != null)
				System.out.println(" Your Order Id " + foodOrder.getId() + " Updated");
			else
				System.err.println("cannot Update the Order as Id is Invalid");
			break;
		}
		case 3: {
			System.out.println("Enter the Order Id to display details");
			FoodOrder foodOrder = foodOrderDao.findById(sc.nextInt());
			if (foodOrder != null) {
				System.out.println("FoodOrder Found");
				System.out.println(foodOrder);
			} else
				System.err.println("Cannot find Order as Id is Invalid");
			break;
		}
		case 4: {
			System.out.println("Enter the Customer Id to display food orders");
			List<FoodOrder> foodOrders = foodOrderDao.findByCustomerId(sc.nextInt());
			if (foodOrders.isEmpty())
				System.err.println("No Orders found for entered Customer Id");
			else
				for (FoodOrder foodOrder : foodOrders) {
					System.out.println(foodOrder);
					System.out.println("--------------------");
				}
			break;
		}
		case 5: {
			System.out.println("Enter the Customer Phone and password to display food orders");
			List<FoodOrder> foodOrders = foodOrderDao.findByCustomer(sc.nextLong(), sc.next());
			if (foodOrders.isEmpty())
				System.err.println("No Orders found for entered Customer");
			else
				for (FoodOrder foodOrder : foodOrders) {
					System.out.println(foodOrder);
					System.out.println("--------------------");
				}
			break;
		}
		default: {
			System.err.println("Invalid Choice");
		}
		}
		sc.close();
		((AnnotationConfigApplicationContext) context).close();
	}
}
