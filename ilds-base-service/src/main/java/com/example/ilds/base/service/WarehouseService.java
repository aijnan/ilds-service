package com.example.wms.base.service;

import com.example.wms.base.model.entity.Warehouse;

import java.util.List;

public interface WarehouseService {

    Warehouse save(Warehouse warehouse);

    List<Warehouse> findAll();

    void delete(String id);

    Warehouse getWareHouseById(String id);
}
