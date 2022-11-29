package org.example.db.model;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "insurances")
public class Insurance {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    long id; // - id bigint PRIMARY KEY NOT NULL


    @Column(insertable=false, updatable=false)
    long vehicleId; //- vehicle_id bigint NOT NULL REFERENCES (vehicles.id)

    @Column
    String insurer; //- insurer text NOT NULL //nazwa towarzystwa ubezpieczeniowego z którego pochodzi dana oferta

    @Column
    float price; //- price float NOT NULL

    @Column
    String additionalData; // - ... // pozostałe dane o ofercie ubezpieczeniowej

    @Column
    LocalDateTime insertTime; //- insert_time timestamp NOT NULL

    @ManyToOne
    @JoinColumn( name = "vehicle_id", nullable = false )
    Vehicle vehicle;

    public Insurance(long idInsurance, long idVehicleInsurance, String insurer, float price, String additionalDataInsurance, Time insertTimeInsurance) {
    }
}