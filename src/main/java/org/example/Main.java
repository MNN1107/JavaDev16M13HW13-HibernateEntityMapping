package org.example;
import org.example.crud.TicketCrudService;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            TicketCrudService ticketCrudService = new TicketCrudService(session);


            Ticket ticketWithNonexistentClient = new Ticket();
            Planet validPlanet = new Planet("MARS", "Mars");
            ticketWithNonexistentClient.setClient(null);
            ticketWithNonexistentClient.setFromPlanet(validPlanet);
            ticketWithNonexistentClient.setToPlanet(validPlanet);
            ticketWithNonexistentClient.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            try {
                ticketCrudService.createTicket(ticketWithNonexistentClient);
                System.out.println("A ticket for a non-existent or null customer was successfully saved.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }


            Ticket ticketWithNonexistentPlanet = new Ticket();
            Client validClient = new Client("Anna Shabalina");
            session.save(validClient);
            ticketWithNonexistentPlanet.setClient(validClient);
            ticketWithNonexistentPlanet.setFromPlanet(null);
            ticketWithNonexistentPlanet.setToPlanet(validPlanet);
            ticketWithNonexistentPlanet.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            try {
                ticketCrudService.createTicket(ticketWithNonexistentPlanet);
                System.out.println("A ticket for a non-existent or null planet was successfully saved.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } finally {
            sessionFactory.close();
        }
    }

}

       

           
