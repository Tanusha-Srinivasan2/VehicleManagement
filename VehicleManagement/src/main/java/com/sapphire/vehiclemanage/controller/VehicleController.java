package com.sapphire.vehiclemanage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapphire.vehiclemanage.model.Vehicle;
import com.sapphire.vehiclemanage.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController

@RequestMapping("/api")
public class VehicleController {
    @Autowired
    private VehicleService service;



    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        return new ResponseEntity<>(service.getAllVehicles(), HttpStatus.OK);

    }
    @GetMapping("/vehicle/{id}")
    public ResponseEntity<Vehicle> getProduct(@PathVariable int vin){
        Vehicle vehicle = service.getVehicleById(vin);
        if (vehicle!=null){
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
    @PostMapping("/vehicle")
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle){
        try{
            Vehicle vehicle1 = service.addVehicle(vehicle);
            return new ResponseEntity<>(vehicle1, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),  HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
//    @GetMapping("/product/{productId}/image")
//    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
//        Vehicle product = service.getProductById(productId);
//        byte[] imageFile = product.getImageDate();
//        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
//
//    }

    @PutMapping("/vehicle/{id}")

    public ResponseEntity<String> updateVehicle(@PathVariable int vin, @RequestPart Vehicle vehicle){
        Vehicle vehicle1 = null;
        try {
            vehicle1 = service.updateVehicle(vin, vehicle);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        if(vehicle1!=null){
            return new ResponseEntity<>("Updated", HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping("/vehicle/{vin}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int vin){
        Vehicle vehicle = service.getVehicleById(vin);
        if(vehicle!=null){
            service.deleteVehicle(vin);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>("Vehicle not found", HttpStatus.BAD_REQUEST);
        }

    }
//    @GetMapping("/product/search")
//    public ResponseEntity<List<Vehicle>> searchProducts(@RequestParam String keyword){
//        System.out.println("searching with " + keyword);
//        List<Vehicle> products = service.searchProducts(keyword);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

}
