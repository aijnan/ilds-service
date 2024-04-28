package com.example.ilds.base.repository;

import com.example.ilds.base.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends JpaRepository<Warehouse, String> {
}
