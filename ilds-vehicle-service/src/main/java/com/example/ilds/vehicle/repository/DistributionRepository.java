package com.example.ilds.vehicle.repository;

import com.example.ilds.vehicle.model.entity.Distribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DistributionRepository extends JpaRepository<Distribution, String> {


    @Query("SELECT d FROM Distribution d WHERE  d.status  =1")
    List<Distribution> findAllUse();

    @Query("SELECT d FROM Distribution d WHERE  d.status  =?1")
    List<Distribution> findByWdId(String id);
    @Query("SELECT driver as name  ,count(*)  as value FROM Distribution d group by driver")
    List<Map<String, Object>> driversStatistics();
    @Query("SELECT status as name  ,count(*) as value FROM Distribution d group by status")
    List<Map<String, Object>> deliveryStatistics();
}
