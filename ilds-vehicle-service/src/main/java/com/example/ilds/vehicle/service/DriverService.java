package com.example.ilds.vehicle.service;

import com.example.ilds.vehicle.model.entity.Driver;

import java.util.List;

public interface DriverService {

    Driver save(Driver driver);

    void update(Driver driver);

    void delete(String id);

    Driver findById(String id);

    List<Driver> findAll();

}
