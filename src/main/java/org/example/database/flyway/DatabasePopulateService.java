package org.example.database.flyway;

import org.flywaydb.core.Flyway;

public class DatabasePopulateService {
    public static void main(String[] args) {
        populateDatabase();
    }
    public static void populateDatabase(){
        try {
            Flyway flyway = Flyway
                    .configure()
                    .dataSource("jdbc:h2:~/test", "", "")
                    .load();

            flyway.migrate();

            System.out.println("Database populated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}