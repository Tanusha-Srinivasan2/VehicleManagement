package com.sapphire.vehiclemanage.service;

import com.sapphire.vehiclemanage.model.Vehicle;
import com.sapphire.vehiclemanage.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepo repo;

    public List<Vehicle> getAllVehicles() {
        return repo.findAll();

    }

    public Vehicle getVehicleById(int vin) {
        return repo.findById(vin).orElse(null);
    }

    public Vehicle addVehicle(Vehicle vehicle) throws IOException {

        return repo.save(vehicle);
    }

    public Vehicle updateVehicle(int vin, Vehicle vehicle) throws IOException {

        Vehicle existingVehicle = repo.findById(vin).orElseThrow(() -> new RuntimeException("Vehicle not found"));


        //vehicle.setId(vin);




        return repo.save(vehicle);
    }


    public void deleteVehicle(int vin) {
        repo.deleteById(vin);
    }



}
