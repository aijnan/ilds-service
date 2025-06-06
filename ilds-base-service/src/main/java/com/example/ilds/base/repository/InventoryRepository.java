package com.example.ilds.base.repository;

import com.example.ilds.base.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {

    Inventory findByWidAndCid(String wid, String cid);

    List<Inventory> findAllByCid(String cid);

    List<Inventory> findAllByWid(String wid);
}
