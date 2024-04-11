import org.example.crud.TicketCrudService;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class TicketCrudServiceJunitTest {
    private SessionFactory sessionFactory;
    private Session session;
    private TicketCrudService ticketCrudService;

    @Before
    public void setUp() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.properties");
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        ticketCrudService = new TicketCrudService(session);
    }

    @After
    public void tearDown() {
        session.close();
        sessionFactory.close();
    }

    @Test(expected = NullPointerException.class)
    public void saveTicketWithNullClient() {
        Transaction transaction = session.beginTransaction();
        Ticket ticket = new Ticket();
        ticket.setClient(null);
        Planet fromPlanet = new Planet("MARS", "Mars");
        Planet toPlanet = new Planet("VEN", "Venus");
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);
        ticketCrudService.createTicket(ticket);
        transaction.commit();
    }

    @Test(expected = NullPointerException.class)
    public void saveTicketWithNullFromPlanet() {
        Transaction transaction = session.beginTransaction();
        Ticket ticket = new Ticket();
        Client client = new Client();
        client.setName("Test Client");
        session.save(client);
        ticket.setClient(client);
        ticket.setFromPlanet(null);
        Planet toPlanet = new Planet("VEN", "Venus");
        ticket.setToPlanet(toPlanet);
        ticketCrudService.createTicket(ticket);
        transaction.commit();
    }

    @Test
    public void testTicketCrudOperations() {

        Transaction transaction = session.beginTransaction();
        Ticket ticket = new Ticket();
        Client client = new Client();
        client.setName("Test Client");
        session.save(client);
        Planet fromPlanet = new Planet("MARS", "Mars");
        Planet toPlanet = new Planet("VEN", "Venus");
        session.save(fromPlanet);
        session.save(toPlanet);
        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);
        ticketCrudService.createTicket(ticket);
        transaction.commit();


        Ticket retrievedTicket = ticketCrudService.getTicketById(ticket.getId());
        assert (retrievedTicket != null);


        transaction = session.beginTransaction();
        retrievedTicket.setFromPlanet(new Planet("JUP", "Jupiter"));
        ticketCrudService.updateTicket(retrievedTicket);
        transaction.commit();


        transaction = session.beginTransaction();
        ticketCrudService.deleteTicket(retrievedTicket);
        transaction.commit();


        assertNull(ticketCrudService.getTicketById(retrievedTicket.getId()));
    }
}
