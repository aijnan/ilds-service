package com.example.wms.base.service.impl;

import com.example.wms.base.model.entity.Inventory;
import com.example.wms.base.model.entity.Warehouse;
import com.example.wms.base.repository.InventoryRepository;
import com.example.wms.base.repository.WareHouseRepository;
import com.example.wms.base.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Resource
    private WareHouseRepository wareHouseRepository;
    @Resource
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> findByCommodityId(String cid) {
        return inventoryRepository.findAllByCid(cid);
    }

    @Override
    public List<Inventory> findByWarehouseId(String wid) {
        List<Inventory> inventoryRecords =  inventoryRepository.findAllByWid(wid);
        if(inventoryRecords != null &&  inventoryRecords.size()>0){
            for (Inventory inventoryRecord : inventoryRecords) {
                Warehouse wareHouse = wareHouseRepository.findById(wid).get();
                inventoryRecord.setLocation(wareHouse.getAddress());
            }
        }

        return  inventoryRecords;
    }

}
