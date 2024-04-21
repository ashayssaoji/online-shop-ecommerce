package com.jsp.ecommerce.dao;

import java.util.List;

import com.jsp.ecommerce.model.Product;

public interface ProductDao {
    Product findById(int id);
    void save(Product product);
    void update(Product product);
    void delete(Product product);
    List<Product> findAll();
}