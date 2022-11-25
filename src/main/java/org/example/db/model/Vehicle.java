package org.example.db.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
public class Vehicle {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    @NotNull
    @Column
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

}
