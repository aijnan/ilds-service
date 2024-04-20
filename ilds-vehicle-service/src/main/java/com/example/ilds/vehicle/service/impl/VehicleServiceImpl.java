package com.example.ilds.vehicle.service.impl;

import com.example.ilds.vehicle.repository.VehicleRepository;
import com.example.ilds.vehicle.model.entity.Vehicle;
import com.example.ilds.vehicle.service.VehicleService;
import com.example.ilds.vehicle.util.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(Vehicle vehicle) {
        vehicle.setCreateAt(DataTimeUtil.getNowTimeString());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void update(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Vehicle findById(String id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> findAllByLikeName(String name) {
        return vehicleRepository.findByNumberLike("%" + name + "%");
    }

}
