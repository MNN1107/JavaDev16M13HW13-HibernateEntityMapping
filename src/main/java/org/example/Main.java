package org.example;

import org.example.crud.ClientCrudService;
import org.example.crud.PlanetCrudService;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()){
            Client client = new Client();
            client.setName("Kyrylo Dobrohot");

            Planet planet = new Planet();
            planet.setId("MARS");
            planet.setName("Mars");

            ClientCrudService clientCrudService = new ClientCrudService(session);
            PlanetCrudService planetCrudService = new PlanetCrudService(session);

            Transaction transaction = session.beginTransaction();
            clientCrudService.createClient(client);
            planetCrudService.createPlanet(planet);
            transaction.commit();

            Client retrievedClient = clientCrudService.getClientById(client.getId());
            Planet retrievedPlanet = planetCrudService.getPlanetById(planet.getId());

            System.out.println("Retrieved Client: " + retrievedClient.getName());
            System.out.println("Retrieved Planet: " + retrievedPlanet.getName());

            retrievedClient.setName("Anna Shabalina");
            clientCrudService.updateClient(retrievedClient);

            planetCrudService.deletePlanet(retrievedPlanet);
        }finally{
            sessionFactory.close();
        }

    }
}