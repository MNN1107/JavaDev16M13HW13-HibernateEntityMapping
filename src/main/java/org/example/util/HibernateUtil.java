package org.example.util;

import lombok.Getter;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    @Getter
    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void main(String[] args) {
        HibernateUtil util =  HibernateUtil.getInstance();

        Session session = util.getSessionFactory().openSession();
        Client client = session.get(Client.class, 1L);
        System.out.println("client = " + client);
        session.close();


    }
}

