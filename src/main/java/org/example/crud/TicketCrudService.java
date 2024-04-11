package org.example.crud;

import org.example.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketCrudService {
    private final Session session;

    public TicketCrudService(Session session) {
        this.session = session;
    }

    public void createTicket(Ticket ticket) {
        Transaction transaction = session.beginTransaction();

        if (ticket.getClient() == null || ticket.getClient().getId() == null) {
            throw new IllegalArgumentException("Ticket client must be specified");
        }
        if (ticket.getFromPlanet() == null || ticket.getFromPlanet().getId() == null) {
            throw new IllegalArgumentException("Ticket fromPlanet must be specified");
        }
        if (ticket.getToPlanet() == null || ticket.getToPlanet().getId() == null) {
            throw new IllegalArgumentException("Ticket toPlanet must be specified");
        }

        session.save(ticket);
        transaction.commit();

    }

    public Ticket getTicketById(Long id) {
        return session.get(Ticket.class, id);
    }

    public void updateTicket(Ticket ticket) {
        Transaction transaction = session.beginTransaction();
        session.update(ticket);
        transaction.commit();
    }

    public void deleteTicket(Ticket ticket) {
        Transaction transaction = session.beginTransaction();
        session.delete(ticket);
        transaction.commit();
    }

    public List<Ticket> getAllTickets() {
        return session.createQuery("FROM ticket", Ticket.class).getResultList();
    }
}