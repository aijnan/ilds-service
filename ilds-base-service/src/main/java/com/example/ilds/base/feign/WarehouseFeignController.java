package com.example.ilds.base.feign;

import com.example.ilds.base.model.entity.Warehouse;
import com.example.ilds.base.service.WarehouseService;
import com.example.ilds.api.base.feign.WarehouseFeignClient;
import com.example.ilds.api.base.vo.WarehouseVO;
import com.example.common.util.BeanUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WarehouseFeignController implements WarehouseFeignClient {
    private final WarehouseService warehouseService;

    @Override
    public WarehouseVO findById(String id) {
        Warehouse warehouse = warehouseService.getWareHouseById(id);
        return BeanUtil.map(warehouse, WarehouseVO.class);
    }
}
