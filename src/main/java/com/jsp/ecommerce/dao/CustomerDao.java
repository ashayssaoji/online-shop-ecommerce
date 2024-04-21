package com.jsp.ecommerce.dao;

import java.util.List;

import com.jsp.ecommerce.model.Customer;

public interface CustomerDao {
    Customer findById(int id);
    void save(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
    List<Customer> findAll();
}