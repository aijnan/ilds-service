package com.example.ilds.vehicle.controller;

import com.example.common.annotation.Log;
import com.example.common.model.enums.BusincessType;
import com.example.ilds.vehicle.model.entity.Distribution;
import com.example.ilds.vehicle.repository.VehicleRepository;
import com.example.ilds.vehicle.service.DistributionService;
import com.example.ilds.vehicle.model.entity.Vehicle;
import com.example.ilds.vehicle.repository.DriverRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/distribution")
public class DistributionController {

    @Resource
    private DistributionService distributionService;

    @Resource
    private DriverRepository driverRepository;

    @Resource
    private VehicleRepository vehicleRepository;


    @Log(moudle = "配送管理",type = BusincessType.INSERT)
    @PostMapping("")
    public Distribution save(@RequestBody Distribution distribution) throws Exception {
        return distributionService.save(distribution);
    }

    @Log(moudle = "配送管理",type = BusincessType.QUERY)
    @GetMapping("")
    public List<Distribution> findAll() {
        return distributionService.findAll();
    }
    @Log(moudle = "查询配送的车辆",type = BusincessType.QUERY)
    @GetMapping("/findAllUsed")
    public List<Distribution> findAllUsed() {
        return distributionService.findAllUsed();
    }


    @GetMapping("can")
    public Map<String, Object> can() {
        Map<String, Object> map = new HashMap<>();
        map.put("drivers", driverRepository.findAll());
        map.put("vehicles", vehicleRepository.findAll());
        return map;
    }
    @GetMapping("/deliveryStatistics")
    public Map<String, Object> deliveryStatistics() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> drivers = distributionService.driversStatistics();
        List<String> dvx = new ArrayList<>();
        List<Long> dvy = new ArrayList<>();
        if(drivers != null && drivers.size() >0){
            for (Map<String, Object> dv : drivers) {
                String name = (String) dv.get("name");
                Long value = (Long) dv.get("value");
                dvx.add(name);
                dvy.add(value);

            }
        }
        Map<String,List> dvxMap = new HashMap<>();
        dvxMap.put("dvx",dvx);   dvxMap.put("dvy",dvy);
        map.put("drivers",dvxMap );
        List<Map<String,Object>>  ds =  distributionService.deliveryStatistics();
        List<Map<String,Object>> newDs = new ArrayList<>();
        if(ds != null && ds.size() >0){
            for (Map<String, Object> d : ds) {
                Integer name = (Integer) d.get("name");

                Map<String,Object> newMap = new HashMap<>();
                newMap.put("name",name == 2 ? "已完成配送" : "未完成配送") ;
                newMap.put("value", d.get("value"));
                newDs.add(newMap);
            }
        }
        map.put("distributions",newDs);
        return map;
    }

    @GetMapping("/getOptimalVehicle/{id}")
    public Vehicle getOptimalVehicle(@PathVariable String id) {
        return distributionService.getOptimalVehicle(id);
    }
}
