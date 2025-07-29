package com.sapphire.vehiclemanage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vin;

    private String Manufacturer;
    private String model;
    private int fleetid;

    private String Owner;

    private String status;

}



