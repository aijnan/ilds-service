package com.example.wms.base.service.impl;

import com.example.wms.base.model.entity.Warehouse;
import com.example.wms.base.repository.WareHouseRepository;
import com.example.wms.base.service.WarehouseService;
import com.example.common.util.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Resource
    private WareHouseRepository wareHouseRepository;

    @Override
    public Warehouse save(Warehouse warehouse) {
        warehouse.setCreateAt(DataTimeUtil.getNowTimeString());
        return wareHouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> findAll() {
        return wareHouseRepository.findAll();
    }

    @Override
    public void delete(String id) {
        wareHouseRepository.deleteById(id);
    }

    @Override
    public Warehouse getWareHouseById(String id) {
        return wareHouseRepository.findById(id).get();
    }


}
