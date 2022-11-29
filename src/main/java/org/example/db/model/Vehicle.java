package org.example.db.model;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;


    @NotNull
    @Column(insertable=false, updatable=false)
    String login; //- login text NOT NULL REFERENCES(users.login)

    @Column
    String brand;

    @Column
    String model;

    @Column
    String additionalData;

    @NotNull
    @Column
    LocalDateTime insertTime; //- insert_time timestamp NOT NULL

    @ManyToOne
    @JoinColumn( name = "login", nullable = false )
    User user;

    @OneToMany(mappedBy = "vehicle")
    List<Insurance> insurances;

    public Vehicle(long idVehicle, String login, String brand, String model, String additionalData, Time insertTimeVehicle) {
    }
}