package com.example.ilds.base.controller;

import com.example.ilds.base.model.entity.Commodity;
import com.example.ilds.base.service.CommodityService;
import com.example.common.annotation.Log;
import com.example.common.model.enums.BusincessType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/commodity")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_COMMODITY')")
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @Log(moudle = "商品管理",type = BusincessType.INSERT)
    @PostMapping("")
    public Commodity save(@RequestBody Commodity commodity) {
        return commodityService.save(commodity);
    }

    @Log(moudle = "商品管理",type = BusincessType.DELETE)
    @DeleteMapping("")
    public void delete(String id) {
        commodityService.delete(id);
    }

    @Log(moudle = "商品管理",type = BusincessType.UPDATE)
    @PutMapping("")
    public void update(@RequestBody Commodity commodity) {
        commodityService.update(commodity);
    }

    @Log(moudle = "商品管理",type = BusincessType.QUERY)
    @GetMapping("")
    public List<Commodity> findAll() {
        return commodityService.findAll();
    }

    @Log(moudle = "商品管理",type = BusincessType.QUERY)
    @GetMapping("/search/{name}")
    public List<Commodity> findByLikeName(@PathVariable String name) {
        return commodityService.findAllByLikeName(name);
    }

    @Log(moudle = "商品管理",type = BusincessType.QUERY)
    @GetMapping("/{id}")
    public Commodity findById(@PathVariable String id) {
        return commodityService.findById(id);
    }


}
