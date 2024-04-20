package com.example.ilds.vehicle.service;

import com.example.ilds.vehicle.model.entity.Distribution;
import com.example.ilds.vehicle.model.entity.Vehicle;

import java.util.List;
import java.util.Map;

public interface DistributionService {

    Distribution save(Distribution distribution) throws Exception;

    List<Distribution> findAll();
    List<Distribution> findAllUsed();
    List<Distribution> findByWdId( String id);

    List<Map<String,Object>> driversStatistics();

    List<Map<String,Object>> deliveryStatistics();

    Vehicle getOptimalVehicle(String id);
}
