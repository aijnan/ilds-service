package com.example.ilds.system.repository;

import com.example.ilds.system.model.entity.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemLogRepository extends JpaRepository<SystemLog,String>, JpaSpecificationExecutor<SystemLog> {
}
