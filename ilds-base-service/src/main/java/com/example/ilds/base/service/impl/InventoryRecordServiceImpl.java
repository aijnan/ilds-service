package com.example.ilds.base.service.impl;

import com.example.ilds.base.model.vo.CommodityChartVo;
import com.example.ilds.base.repository.InventoryRecordRepository;
import com.example.ilds.base.repository.InventoryRepository;
import com.example.ilds.base.model.entity.Commodity;
import com.example.ilds.base.model.entity.Inventory;
import com.example.ilds.base.model.entity.InventoryRecord;
import com.example.ilds.base.repository.CommodityRepository;
import com.example.ilds.base.service.InventoryRecordService;
import com.example.common.util.DataTimeUtil;
import com.example.ilds.api.vehicle.dto.DistributionDTO;
import com.example.ilds.api.vehicle.feign.DistributionFeignClient;
import com.example.ilds.api.vehicle.vo.VehicleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class InventoryRecordServiceImpl implements InventoryRecordService {

    @Resource
    private InventoryRepository inventoryRepository;

    @Resource
    private CommodityRepository commodityRepository;

    @Resource
    private InventoryRecordRepository recordRepository;

    @Resource
    private DistributionFeignClient distributionFeignClient;


    @Override
    public List<CommodityChartVo> analyzeCommodity(Integer type) {
        List<CommodityChartVo> result = new ArrayList<>();
        List<InventoryRecord> all = recordRepository.findAllByType(type);
        Map<String, Integer> map = new HashMap<>();
        for (InventoryRecord r : all) {
            if (map.containsKey(r.getName())) {
                map.put(r.getName(), map.get(r.getName()) + r.getCount());
            } else {
                map.put(r.getName(), r.getCount());
            }
        }
        for (String key : map.keySet()) {
            result.add(new CommodityChartVo(map.get(key), key));
        }
        return result;
    }

    @Override
    public List<InventoryRecord> findAllByWarehouseId(String wid) {
        return recordRepository.findAllByWid(wid);
    }

    @Override
    public List<InventoryRecord> findAllByCommodityId(String cid) {
        return recordRepository.findAllByCid(cid);
    }

    @Override
    public InventoryRecord out(InventoryRecord record) throws Exception {

        //查找当前商品在该仓库的库存
        Inventory inventory = inventoryRepository.findByWidAndCid(record.getWid(), record.getCid());
        //查询结果为空
        if (inventory == null) throw new Exception("仓库内不存在该商品");
        //比较库存
        if (inventory.getCount() < record.getCount()) throw new Exception("出库失败，库存数量不足");

        Optional<Commodity> optional = commodityRepository.findById(record.getCid());
        if (optional == null) {
            throw new Exception("不存在的商品id");
        }
        Commodity commodity = optional.get();
        commodity.setCount(commodity.getCount() - record.getCount());
        commodityRepository.save(optional.get());
        inventory.setCount(inventory.getCount() - record.getCount());

        inventoryRepository.save(inventory);
        record.setCreateAt(DataTimeUtil.getNowTimeString());
        record.setType(-1);
        DistributionDTO distribution = new DistributionDTO();
        distribution.setStatus(1);
        distribution.setAddress(record.getShipAddress());
        distribution.setPhone(record.getPhone());

        VehicleVO vehicle = distributionFeignClient.getOptimalVehicle(record.getWid());

        if(vehicle != null){
            distribution.setDepartureAddress(record.getDepartureAddress());
            distribution.setVid(vehicle.getId());
            distribution.setDriver(vehicle.getDriver());
            distribution.setNumber(vehicle.getNumber());
            distribution.setCare(record.getDescription());
            record.setDriver("司机："+vehicle.getDriver()+ "车牌号：" +vehicle.getNumber());
        }
        distributionFeignClient.saveDistribution(distribution);
        return recordRepository.save(record);
    }

    @Override
    public InventoryRecord in(InventoryRecord record) throws Exception {
        Optional<Commodity> optional = commodityRepository.findById(record.getCid());
        if (optional == null) {
            throw new Exception("不存在的商品id");
        }
        Commodity commodity = optional.get();
        commodity.setCount(commodity.getCount() + record.getCount());
        commodityRepository.save(optional.get());

        //查找当前商品在该仓库的库存
        Inventory inventory = inventoryRepository.findByWidAndCid(record.getWid(), record.getCid());
        //查询结果为空
        if (inventory == null) {
            //新建该商品库存信息
            inventory = new Inventory();
            inventory.setCid(record.getCid());
            inventory.setWid(record.getWid());
            inventory.setCount(0);
            inventory.setName(record.getName());
        }
        inventory.setCount(inventory.getCount() + record.getCount());
        inventoryRepository.save(inventory);
        record.setCreateAt(DataTimeUtil.getNowTimeString());
        record.setType(+1);
        return recordRepository.save(record);
    }
}
