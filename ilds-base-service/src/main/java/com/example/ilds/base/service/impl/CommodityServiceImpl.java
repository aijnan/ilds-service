package com.example.ilds.base.service.impl;

import com.example.ilds.base.model.entity.Commodity;
import com.example.ilds.base.service.CommodityService;
import com.example.ilds.base.repository.CommodityRepository;
import com.example.common.util.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityRepository commodityRepository;

    @Override
    public Commodity save(Commodity commodity) {
        commodity.setCreateAt(DataTimeUtil.getNowTimeString());
        return commodityRepository.save(commodity);
    }

    @Override
    public void update(Commodity commodity) {
        commodity.setUpdateAt(DataTimeUtil.getNowTimeString());
        commodityRepository.save(commodity);
    }

    @Override
    public void delete(String id) {
        commodityRepository.deleteById(id);
    }

    @Override
    public Commodity findById(String id) {
        return commodityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Commodity> findAll() {
        return commodityRepository.findAll();
    }

    @Override
    public List<Commodity> findAllByLikeName(String name) {
        return commodityRepository.findByNameLike("%" + name + "%");
    }

}
