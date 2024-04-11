package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "from_planet_id")
    private Planet fromPlanet;

    @ManyToOne
    @JoinColumn(name = "to_planet_id")
    private Planet toPlanet;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    public Client getClient() {
        return client;
    }

    public Planet getFromPlanet() {
        return fromPlanet;
    }

    public Planet getToPlanet() {
        return toPlanet;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public void setFromPlanet(Planet fromPlanet) {
        this.fromPlanet = fromPlanet;
    }

    public void setToPlanet(Planet toPlanet) {
        this.toPlanet = toPlanet;
    }
    public Long getId() {
        return id;
    }

}
