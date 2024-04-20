package com.example.wms.base.feign;

import com.example.wms.base.model.entity.Warehouse;
import com.example.wms.base.service.WarehouseService;
import com.example.wms.api.base.feign.WarehouseFeignClient;
import com.example.wms.api.base.vo.WarehouseVO;
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
