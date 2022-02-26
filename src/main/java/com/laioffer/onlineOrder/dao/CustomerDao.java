package com.laioffer.onlineOrder.dao;

import com.laioffer.onlineOrder.entity.Authorities;
import com.laioffer.onlineOrder.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void signUp(Customer customer) {
        Session session = null;
        Authorities authorities = new Authorities();
        authorities.setEmail(customer.getEmail());
        authorities.setAuthorities("ROLE_USER");

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            //因为cascade, 里面有关联的save动作， 所以cart不需要在写一次save
            session.getTransaction().commit();

        } catch (Exception ex) {
            if (session != null) {
                ex.printStackTrace();
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();

            }
        }
    }

    public Customer getCustomer(String email) {
        Customer customer = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            customer = session.get(Customer.class, email); //去customer表里面搜有没有email
            //transaction用于多线程，一般是写操作用的多一些。
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        //JAVA 8之后有个特性可以自动close
        return customer;
    }
}
