package com.jsp.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecommerce.model.Customer;
import com.jsp.ecommerce.model.ShoppingOrder;

public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Integer> {

	List<ShoppingOrder> findByCustomer(Customer customer);

}
