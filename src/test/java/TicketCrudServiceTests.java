import org.example.crud.TicketCrudService;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TicketCrudServiceTests {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.properties");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        TicketCrudService ticketCrudService = new TicketCrudService(session);

        try {
            Transaction transaction = session.beginTransaction();

            Ticket ticket1 = new Ticket();
            Client nonexistentClient = null;
            ticket1.setClient(nonexistentClient);
            Planet validPlanet = new Planet("MARS", "Mars");
            ticket1.setFromPlanet(validPlanet);
            ticket1.setToPlanet(validPlanet);
            ticketCrudService.createTicket(ticket1);


            Ticket ticket2 = new Ticket();
            Client validClient = new Client("Test Client");
            session.save(validClient);
            Planet nonexistentPlanet = null;
            ticket2.setClient(validClient);
            ticket2.setFromPlanet(nonexistentPlanet);
            ticket2.setToPlanet(nonexistentPlanet);
            ticketCrudService.createTicket(ticket2);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
