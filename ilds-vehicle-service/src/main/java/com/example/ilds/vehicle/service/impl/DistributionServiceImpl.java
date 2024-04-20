package com.example.ilds.vehicle.service.impl;
import com.example.ilds.api.base.feign.WarehouseFeignClient;
import com.example.ilds.api.base.vo.WarehouseVO;
import com.example.ilds.vehicle.model.entity.Distribution;
import com.example.ilds.vehicle.repository.VehicleRepository;
import com.example.ilds.vehicle.util.GeoLocationUtil;
import com.example.ilds.vehicle.model.entity.Driver;
import com.example.ilds.vehicle.model.entity.Vehicle;
import com.example.ilds.vehicle.repository.DistributionRepository;
import com.example.ilds.vehicle.repository.DriverRepository;
import com.example.ilds.vehicle.service.DistributionService;
import com.example.ilds.vehicle.util.MapKeyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DistributionServiceImpl implements DistributionService {

    @Resource
    private DistributionRepository distributionRepository;

    @Resource
    private DriverRepository driverRepository;

    @Resource
    private VehicleRepository vehicleRepository;

    @Resource
    private WarehouseFeignClient warehouseFeignClient;
    @Resource
    private DistributionRepository distributionService;

    @Override
    public Distribution save(Distribution distribution) throws Exception {
        if (distributionRepository.findById(distribution.getId()) == null) {
            Optional<Driver> driver = driverRepository.findById(distribution.getDid());
            Optional<Vehicle> vehicle = vehicleRepository.findById(distribution.getVid());
//            if (driver.isEmpty() || vehicle.isEmpty()) throw new Exception("请求参数错误");
            if (driver.get().isDriving() || vehicle.get().isDriving()) throw new Exception("司机或货车状态不可用");
            driverRepository.updateDriving(true, distribution.getDid());
            vehicleRepository.updateDriving(true, distribution.getVid());
        }
        return distributionRepository.save(distribution);
    }

    @Override
    public List<Distribution> findAll() {
        return distributionRepository.findAll();
    }

    @Override
    public List<Distribution> findAllUsed() {
        return distributionRepository.findAllUse();
    }

    @Override
    public List<Distribution> findByWdId(String id) {
        return distributionRepository.findByWdId(id);
    }

    @Override
    public List<Map<String, Object>> driversStatistics() {
        return distributionRepository.driversStatistics();
    }

    @Override
    public List<Map<String, Object>> deliveryStatistics() {
        return distributionRepository.deliveryStatistics();
    }

    @Override
    public Vehicle getOptimalVehicle(String id) {
        WarehouseVO warehouse = warehouseFeignClient.findById(id);
        Map<String,Double> distances = new HashMap<>();
        Map<String,Vehicle> vehiclesMap = new HashMap<>();
        if (warehouse.getAddress() != null && !"".equals(warehouse.getAddress())) {
            Vehicle wh = GeoLocationUtil.getAddress(warehouse.getAddress());

            Set<String> usedVehicles = new HashSet<>();
            List<Distribution> distribution = distributionService.findAllUse();
            if (distribution != null && distribution.size() > 0) {
                for (Distribution distribution1 : distribution) {
                    usedVehicles.add(distribution1.getVid());
                }
            }else{
                List<Distribution> distributions = distributionService.findByWdId(id);
                if (distributions != null && distributions.size() > 0) {
                    Distribution distributionFastest = null;
//                    LocalDateTime startDate =  LocalDateTime.now();
                    LocalDateTime startDate = distributions.get(0).getTime();
                    distributionFastest  = distributions.get(0);
                    for (Distribution db : distributions) {
                        if(startDate.isAfter(db.getTime())){
                            startDate = db.getTime();
                            distributionFastest  = db;
                        }
                    }
                    if(startDate != null){
                        return vehicleRepository.findById(distributionFastest.getVid()).get();
                    }
                }

            }

            List<Vehicle> vehicles = vehicleRepository.findAll();
            if (vehicles.size() > 0) {
                for (Vehicle vehicle : vehicles) {
                    if (usedVehicles.contains(vehicle.getId())) {
                        continue;
                    }
                    vehiclesMap.put(vehicle.getId(),vehicle);
                    if (vehicle.getDriver() != null) {
                        Driver driver = driverRepository.findById(vehicle.getDriver()).get();
                        vehicle.setDriver(driver.getName());
                        if (vehicle.getAddress() != null && !"".equals(vehicle.getAddress())) {
                            Vehicle vehicleTmp = GeoLocationUtil.getAddress(vehicle.getAddress());
                            if (vehicleTmp != null && vehicleTmp.getLat() != 0.00 && vehicleTmp.getLng() != 0.00) {

                                double distance = 0;
                                if (wh != null) {
                                    distance = GeoLocationUtil.getDistance(wh.getLng(), wh.getLat(), vehicleTmp.getLng(), vehicleTmp.getLat());
                                }

                                distances.put(vehicle.getId(), distance);
                            }
                        }
                    }

                }
            }
        } else {

        }
        String minKey = MapKeyUtil.getMapMinOrMaxValueKey(distances, "min");
        return  vehiclesMap.get(minKey);
    }

}
