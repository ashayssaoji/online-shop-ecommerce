package com.jsp.ecommerce.dao;

import java.util.List;

import com.jsp.ecommerce.model.Item;

public interface ItemDao {
    Item findById(int id);
    void save(Item item);
    void update(Item item);
    void delete(Item item);
    List<Item> findAll();
}
