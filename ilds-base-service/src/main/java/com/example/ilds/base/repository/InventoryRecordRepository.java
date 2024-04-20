package com.example.wms.base.repository;

import com.example.wms.base.model.entity.InventoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRecordRepository extends JpaRepository<InventoryRecord, String> {

    List<InventoryRecord> findAllByWid(String wid);

    List<InventoryRecord> findAllByType(Integer type);

    List<InventoryRecord> findAllByCid(String cid);
}
