package com.example.wms.base.service;

import com.example.wms.base.model.entity.Commodity;

import java.util.List;

public interface CommodityService {

    Commodity save(Commodity commodity);

    void update(Commodity commodity);

    void delete(String id);

    Commodity findById(String id);

    List<Commodity> findAll();

    List<Commodity> findAllByLikeName(String name);

}
