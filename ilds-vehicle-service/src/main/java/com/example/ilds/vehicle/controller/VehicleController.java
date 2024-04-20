package com.example.ilds.vehicle.controller;

import com.example.common.annotation.Log;
import com.example.common.model.enums.BusincessType;
import com.example.ilds.vehicle.model.entity.Distribution;
import com.example.ilds.vehicle.service.DistributionService;
import com.example.ilds.vehicle.model.entity.Driver;
import com.example.ilds.vehicle.model.entity.Vehicle;
import com.example.ilds.vehicle.service.DriverService;
import com.example.ilds.vehicle.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Resource
    private VehicleService vehicleService;

    @Resource
    private DriverService driverService;

    @Resource
    private DistributionService distributionService;

    @Log(moudle = "车辆管理",type = BusincessType.INSERT)
    @PostMapping("")
    public Vehicle save(@RequestBody Vehicle vehicle) {
        return vehicleService.save(vehicle);
    }

    @Log(moudle = "车辆管理",type = BusincessType.QUERY)
    @GetMapping("")
    public List<Vehicle> findAll() {
        Set<String> usedVehicles = new HashSet<>();
        List<Distribution> distribution = distributionService.findAllUsed();
        if(distribution != null && distribution.size()>0){
            for (Distribution distribution1 : distribution) {
                usedVehicles.add(distribution1.getVid());
            }
        }
        List<Vehicle> vehicles = vehicleService.findAll();
        if(vehicles != null &&  vehicles.size() >0){
            for (Vehicle vehicle : vehicles) {
//                if(usedVehicles.contains(vehicle.getId())){
//                    continue;
//                }
                if(vehicle.getDriver() != null){
                    Driver driver = driverService.findById(vehicle.getDriver());
                    if(driver != null){
                        vehicle.setDriver(driver.getName());
//                        if(driver.getAddress() != null && !"".equals(driver.getAddress())){
//                            Vehicle vehicleTmp = GeoLocationService.getAddress(driver.getAddress());
//                            if(vehicleTmp.getLat() != 0.00  && vehicleTmp.getLng() != 0.00 ){
//
//                            }
//                        }
                    }
                }

            }
    }



        return vehicles;
    }

    @Log(moudle = "车辆管理",type = BusincessType.QUERY)
    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable String id) {
        return vehicleService.findById(id);
    }

    @Log(moudle = "车辆管理",type = BusincessType.DELETE)
    @DeleteMapping("")
    public void delete(String id) {
        vehicleService.delete(id);
    }
    @Log(moudle = "车辆搜索",type = BusincessType.QUERY)
    @GetMapping("/search/{name}")
    public List<Vehicle> findByLikeName(@PathVariable String name) {
        return vehicleService.findAllByLikeName(name);
    }

}
