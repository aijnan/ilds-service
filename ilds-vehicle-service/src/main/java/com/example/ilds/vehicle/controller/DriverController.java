package com.example.ilds.vehicle.controller;

import com.example.common.annotation.Log;
import com.example.common.model.enums.BusincessType;
import com.example.ilds.vehicle.model.entity.Driver;
import com.example.ilds.vehicle.service.DriverService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Resource
    private DriverService driverService;

    @Log(moudle = "驾驶员管理",type = BusincessType.INSERT)
    @PostMapping("")
    public Driver save(@RequestBody Driver driver) {
        return driverService.save(driver);
    }

    @Log(moudle = "驾驶员管理",type = BusincessType.QUERY)
    @GetMapping("")
    public List<Driver> findAll() {
        return driverService.findAll();
    }

    @Log(moudle = "驾驶员管理",type = BusincessType.QUERY)
    @GetMapping("/{id}")
    public Driver findById(@PathVariable String id) {
        return driverService.findById(id);
    }

    @Log(moudle = "驾驶员管理",type = BusincessType.DELETE)
    @DeleteMapping("")
    public void delete(String id) {
        driverService.delete(id);
    }

}
