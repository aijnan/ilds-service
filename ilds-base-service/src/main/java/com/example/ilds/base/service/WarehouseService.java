package com.example.ilds.base.service;

import com.example.ilds.base.model.entity.Warehouse;

import java.util.List;

public interface WarehouseService {

    Warehouse save(Warehouse warehouse);

    List<Warehouse> findAll();

    void delete(String id);

    Warehouse getWareHouseById(String id);
}
