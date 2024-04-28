package com.example.ilds.base.service;

import com.example.ilds.base.model.entity.Inventory;

import java.util.List;

public interface InventoryService {

    Inventory save(Inventory inventory);

    List<Inventory> findAll();

    List<Inventory> findByCommodityId(String cid);

    List<Inventory> findByWarehouseId(String wid);

}
