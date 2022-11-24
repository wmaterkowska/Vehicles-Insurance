package org.example.db.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table( name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id; //- id bigint PRIMARY KEY NOT NULL

    @Column
    String nick; //- nick text NOT NULL

    @Column
    String login; //- login text NOT NULL

    @Column
    String password; //- password text NOT NULL

    @Column
    String additionalData; //- ... // pozostałe dane o użytkowniku

    @Column
    LocalDateTime timestamp; //- insert_time timestamp NOT NULL DEFAULT now()

}
