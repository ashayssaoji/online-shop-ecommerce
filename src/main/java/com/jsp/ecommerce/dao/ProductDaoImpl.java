package com.jsp.ecommerce.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jsp.ecommerce.model.Item;
import com.jsp.ecommerce.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Product product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // Handle exception
            throw e;
        }
    }

    public List<Product> fetchAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        }
    }

    public Product findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        }
    }

    public void delete(Product product) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            // Attach the entity to the session before deletion
            Product managedProduct = session.find(Product.class, product.getId());
            if(managedProduct != null) {
                session.delete(managedProduct);
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Could not delete product with ID " + product.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public List<Product> fetchDisplayProducts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product p WHERE p.display = true", Product.class).list();
        }
    }
    
    public void deleteItem(Item item) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // Handle exception
            throw e;
        }
    }
    @Override
    public void update(Product product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update product", e);
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all products", e);
        }
    }

}
