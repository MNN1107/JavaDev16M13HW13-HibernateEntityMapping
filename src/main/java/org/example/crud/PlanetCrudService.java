package org.example.crud;

import org.example.entity.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlanetCrudService {
    private final Session session;

    public PlanetCrudService(Session session) {
        this.session = session;
    }

    public void createPlanet(Planet planet) {
        Transaction transaction = session.beginTransaction();
        session.save(planet);
        transaction.commit();
    }

    public Planet getPlanetById(String id) {
        return session.get(Planet.class, id);
    }

    public void updatePlanet(Planet planet) {
        Transaction transaction = session.beginTransaction();
        session.update(planet);
        transaction.commit();
    }

    public void deletePlanet(Planet planet) {
        Transaction transaction = session.beginTransaction();
        session.delete(planet);
        transaction.commit();
    }
}
