package org.example.database.flyway;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {
    public static void main(String[] args) {
        initDatabase();
    }

    public static void initDatabase() {
        try {
            Flyway flyway = Flyway
                    .configure()
                    .dataSource("jdbc:h2:~/test", "", "")
                    .load();

            flyway.migrate();

            System.out.println("Database initialized successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
