package com.jsp.ecommerce.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jsp.ecommerce.model.Customer;

@Repository
public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Customer> findByEmailOrMobile(String email, long mobile) {
        try (Session session = sessionFactory.openSession()) {
            Query<Customer> query = session.createQuery("FROM Customer c WHERE c.email = :email OR c.mobile = :mobile", Customer.class);
            query.setParameter("email", email);
            query.setParameter("mobile", mobile);
            return query.list();
        }
    }

    public void save(Customer customer) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
    
    public void update(Customer customer) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Customer findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Customer.class, id);
        } catch (Exception e) {
            // Handle exception appropriately
            throw new RuntimeException("Could not find customer with id: " + id, e);
        }
    }

}
