package com.klef.jfsd.exam;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Insert records (example using persistent object)
        Customer customer1 = new Customer();
        customer1.setName("Alice");
        customer1.setEmail("alice@example.com");
        customer1.setAge(25);
        customer1.setLocation("New York");

        session.beginTransaction();
        session.save(customer1);
        session.getTransaction().commit();

        // Apply restrictions using Criteria API
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("age", 25));
        criteria.add(Restrictions.like("location", "%New%"));

        List<Customer> customers = criteria.list();
        for (Customer customer : customers) {
            System.out.println(customer);
        }

        session.close();
        sessionFactory.close();
    }
}
