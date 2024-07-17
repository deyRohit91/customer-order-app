package org.jsp.customerorderapp;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "org.jsp.customerorderapp" })
public class CustomerOrderConfig {
	@Bean
	public EntityManager entityManager() {
	return Persistence.createEntityManagerFactory("dev").createEntityManager();
	}
}
