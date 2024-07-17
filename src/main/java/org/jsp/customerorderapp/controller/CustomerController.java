package org.jsp.customerorderapp.controller;

import java.util.Scanner;

import org.jsp.customerorderapp.CustomerOrderConfig;
import org.jsp.customerorderapp.dao.CustomerDao;
import org.jsp.customerorderapp.dto.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerController {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(CustomerOrderConfig.class);
		CustomerDao customerDao = context.getBean("customerDao", CustomerDao.class);
		System.out.println("1.Save Customer");
		System.out.println("2.Update Customer");
		System.out.println("3.Find Customer By Id");
		System.out.println("4.Verify Customer By Phone and password");
		System.out.println("5.Verify Customer By email and password");
		Scanner sc = new Scanner(System.in);
		switch (sc.nextInt()) {
		case 1: {
			System.out.println("Enter the name,phone,email,gender,age and password to register");
			Customer customer = new Customer();
			customer.setName(sc.next());
			customer.setPhone(sc.nextLong());
			customer.setEmail(sc.next());
			customer.setGender(sc.next());
			customer.setAge(sc.nextInt());
			customer.setPassword(sc.next());
			customer = customerDao.saveCustomer(customer);
			System.out.println("Customer saved with Id:" + customer.getId());
			break;
		}
		case 2: {
			System.out.println("Enter the id,name,phone,email,gender,age and password to update");
			Customer customer = new Customer();
			customer.setId(sc.nextInt());
			customer.setName(sc.next());
			customer.setPhone(sc.nextLong());
			customer.setEmail(sc.next());
			customer.setGender(sc.next());
			customer.setAge(sc.nextInt());
			customer.setPassword(sc.next());
			customer = customerDao.updateCustomer(customer);
			if (customer != null)
				System.out.println("Customer  with Id:" + customer.getId() + " updated");
			else
				System.err.println("Cannot Update as Customer Id is Invalid");
		}
		case 3: {
			System.out.println("Enter the Customer Id to display details");
			int id = sc.nextInt();
			Customer customer = customerDao.findById(id);
			if (customer != null) {
				System.out.println("Customer Deatails found");
				System.out.println(customer);
			} else
				System.err.println("Invalid Customer Id");
			break;
		}
		case 4: {
			System.out.println("Enter the Phone Number and Password to verify Customer");
			long phone = sc.nextLong();
			String password = sc.next();
			Customer customer = customerDao.verify(phone, password);
			if (customer != null) {
				System.out.println("Customer Verification Succesfull");
				System.out.println(customer);
			} else
				System.err.println("Invalid Phone Number or Password");
			break;
		}
		case 5: {
			System.out.println("Enter the Email Id and Password to verify Customer");
			String email = sc.next();
			String password = sc.next();
			Customer customer = customerDao.verify(email, password);
			if (customer != null) {
				System.out.println("Customer Verification Succesfull");
				System.out.println(customer);
			} else
				System.err.println("Invalid Email Id or Password");
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
