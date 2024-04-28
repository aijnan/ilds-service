package com.example.ilds.system.service;

import com.example.ilds.system.model.entity.SystemLog;
import com.example.ilds.system.model.vo.SystemLogVo;

import java.util.List;

public interface SystemLogService {
    public void record(SystemLog log);
    public List<SystemLog> getAll();
    public void delete(String id);
    public List<SystemLog> query(SystemLogVo systemLogVo);
}
