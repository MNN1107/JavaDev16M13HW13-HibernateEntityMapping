package org.example.crud;

import org.example.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientCrudService {
    private final Session session;

    public ClientCrudService(Session session) {
        this.session = session;
    }

    public void createClient(Client client) {
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
    }

    public Client getClientById(Long id) {
        return session.get(Client.class, id);
    }

    public void updateClient(Client client) {
        Transaction transaction = session.beginTransaction();
        session.update(client);
        transaction.commit();
    }

    public void deleteClient(Client client) {
        Transaction transaction = session.beginTransaction();
        session.delete(client);
        transaction.commit();
    }
}
