package com.jsp.ecommerce.dao;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.jsp.ecommerce.model.Customer;
import com.jsp.ecommerce.model.Item;
import com.jsp.ecommerce.model.PaymentDetails;
import com.jsp.ecommerce.model.Product;
import com.jsp.ecommerce.model.ShoppingCart;
import com.jsp.ecommerce.model.ShoppingOrder;

public class DAO {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/testdatabase?createDatabaseIfNotExist=true");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "password");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                // Add your model classes here
                configuration.addAnnotatedClass(Customer.class);
                configuration.addAnnotatedClass(Item.class);
                configuration.addAnnotatedClass(PaymentDetails.class);
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(ShoppingCart.class);
                configuration.addAnnotatedClass(ShoppingOrder.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
