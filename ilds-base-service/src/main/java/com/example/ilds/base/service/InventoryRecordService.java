package com.example.ilds.base.service;

import com.example.ilds.base.model.vo.CommodityChartVo;
import com.example.ilds.base.model.entity.InventoryRecord;

import java.util.List;

public interface InventoryRecordService {

    //出入库排行统计
    List<CommodityChartVo> analyzeCommodity(Integer type);

    List<InventoryRecord> findAllByWarehouseId(String wid);

    List<InventoryRecord> findAllByCommodityId(String cid);

    //出库
    InventoryRecord out(InventoryRecord record) throws Exception;

    //入库
    InventoryRecord in(InventoryRecord record) throws Exception;
}
