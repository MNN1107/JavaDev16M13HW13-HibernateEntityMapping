package org.example.database.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import static org.example.database.flyway.DatabasePopulateService.populateDatabase;

public class DatabasePopulation {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.properties");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            populateDatabase();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}