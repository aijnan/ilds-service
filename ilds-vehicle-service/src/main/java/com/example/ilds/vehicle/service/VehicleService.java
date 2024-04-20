package com.example.ilds.vehicle.service;

import com.example.ilds.vehicle.model.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    Vehicle save(Vehicle vehicle);

    void update(Vehicle vehicle);

    void delete(String id);

    Vehicle findById(String id);

    List<Vehicle> findAll();

    List<Vehicle> findAllByLikeName(String name);
}
