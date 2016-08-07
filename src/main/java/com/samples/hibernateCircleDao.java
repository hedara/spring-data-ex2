package com.samples;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Created by edara on 8/6/16.
 */
@Repository
public class HibernateCircleDao {
    @Autowired
    SessionFactory sessionFactory;

    public long getCircleCount() {
        if(sessionFactory == null) {
            System.out.println("session factory is null");
        }
        Query query = sessionFactory.openSession().createQuery("select count(*) from Circle");
        return (Long) query.uniqueResult();
    }
}
