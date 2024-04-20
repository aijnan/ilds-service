package com.example.wms.system.repository;

import com.example.wms.system.model.entity.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemLogRepository extends JpaRepository<SystemLog,String>, JpaSpecificationExecutor<SystemLog> {
}
